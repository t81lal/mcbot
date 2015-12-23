package org.topdank.bot.net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.topdank.bot.Protocol;
import org.topdank.bot.auth.Session;
import org.topdank.bot.eventbus.EventBus;
import org.topdank.bot.net.event.connection.ConnectedEvent;
import org.topdank.bot.net.event.disconnect.DisconnectedEvent;
import org.topdank.bot.net.event.disconnect.DisconnectingEvent;
import org.topdank.bot.net.event.disconnect.TimeoutEvent;
import org.topdank.bot.net.event.disconnect.TimeoutType;
import org.topdank.bot.net.event.packet.PacketReceivedEvent;
import org.topdank.bot.net.event.packet.PacketSentEvent;
import org.topdank.bot.net.packet.ReadablePacket;
import org.topdank.bot.net.packet.WriteablePacket;
import org.topdank.bot.net.packet.codecs.PacketCompressionCodec;
import org.topdank.bot.net.packet.codecs.PacketEncryptorCodec;
import org.topdank.bot.net.packet.codecs.PacketReaderCodec;
import org.topdank.bot.net.packet.codecs.PacketSizerCodec;
import org.topdank.bot.net.packet.codecs.PacketWriterCodec;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ConnectTimeoutException;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.TimeoutException;
import io.netty.handler.timeout.WriteTimeoutHandler;

public class TcpClient<T extends Session> extends Client<T> {

	protected final List<ReadablePacket> packets;
	private Channel channel;
	private boolean disconnected;
	private boolean writing;
	private EventLoopGroup group;
	private Bootstrap bootstrap;

	public TcpClient(T session, String host, int port, Protocol protocol, EventBus bus) throws IOException {
		super(session, host, port, protocol, bus);
		packets = new ArrayList<ReadablePacket>();
	}
	
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public boolean isDisconnected() {
		return disconnected;
	}

	public void setDisconnected(boolean disconnected) {
		this.disconnected = disconnected;
	}

	public boolean isWriting() {
		return writing;
	}

	public void setWriting(boolean writing) {
		this.writing = writing;
	}

	@Override
	public void connect() throws IOException {
		connect(true);
	}

	@Override
	public void connect(boolean wait) throws IOException {
		if (disconnected || (bootstrap != null))
			throw new IllegalStateException("Client already disconnected");

		bootstrap = new Bootstrap();
		group = new NioEventLoopGroup();
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 25000);

		bootstrap.handler(new ChannelInitializer<Channel>() {
			@Override
			public void initChannel(Channel channel) throws Exception {
				TcpClient.this.channel = channel;
				setupChannel(channel.pipeline());
			}
		}).group(group).remoteAddress(getHost(), getPort());
		
		AtomicBoolean calledTimeout = new AtomicBoolean(false);
		try {
			bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 25000);
			ChannelFuture future = bootstrap.connect();
			if (!future.isSuccess()) {
				new Thread() {
					@Override
					public void run() {
						while (true) {
							Throwable t = future.cause();
							if (t != null) {
								future.cause().printStackTrace();
								break;
							}
						}
					}
				}.start();
			}
			bootstrap = null;
			if (wait) {
				while ((channel == null) && !disconnected) {
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				future.addListener(new ChannelFutureListener() {
					@Override
					public void operationComplete(ChannelFuture channelFuture) throws Exception {
						if ((channelFuture.cause() instanceof ConnectTimeoutException) && !calledTimeout.get()) {
							calledTimeout.set(true);
							getProtocol().onTimeout(new TimeoutEvent(TcpClient.this, TimeoutType.CONNECT));
						}
					}
				});
			}
		} catch (Exception e) {
			if ((e instanceof ConnectTimeoutException) && !calledTimeout.get()) {
				calledTimeout.set(true);
				getProtocol().onTimeout(new TimeoutEvent(TcpClient.this, TimeoutType.CONNECT));
			} else {
				System.err.println("Failed to establish connection.");
				e.printStackTrace();
			}
		}
	}

	protected void setupChannel(ChannelPipeline pipeline) {
		pipeline.channel().config().setOption(ChannelOption.IP_TOS, 0x18);
		pipeline.channel().config().setOption(ChannelOption.TCP_NODELAY, false);

		pipeline.addFirst("readTimeout", new ReadTimeoutHandler(10));
		pipeline.addFirst("writeTimeout", new WriteTimeoutHandler(10));

		if (getProtocol().requiresEncryption()) {
			pipeline.addLast("encrypter", new PacketEncryptorCodec(TcpClient.this));
		}
		if (getProtocol().requiresPacketSizer()) {
			pipeline.addLast("sizer", new PacketSizerCodec(TcpClient.this));
		}
		
		pipeline.addLast("readerCodec", new PacketReaderCodec(TcpClient.this));
		pipeline.addLast("writerCodec", new PacketWriterCodec(TcpClient.this));
		pipeline.addLast("manager", TcpClient.this);
	}

	@Override
	public void setCompression(int ct) {
		super.setCompression(ct);
		if (getCompression() >= 0) {
			if (channel.pipeline().get("compression") == null) {
				channel.pipeline().addBefore("readerCodec", "compression", new PacketCompressionCodec(this));
			}
		}
	}

	@Override
	public void disconnect(String reason) {
		if (disconnected) {
			return;
		}
		
		disconnected = true;
		if (writing) {
			while (writing) {
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (reason == null) {
			reason = "Connection closed.";
		}
		if (channel != null) {
			if (channel.isOpen()) {
				getProtocol().onDisconnecting(new DisconnectingEvent(this, reason));
			}
			try {
				channel.close().syncUninterruptibly();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		getProtocol().onDisconnected(new DisconnectedEvent(this, reason));
		if (group != null) {
			try {
				group.shutdownGracefully();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		channel = null;
	}

	@Override
	public void send(WriteablePacket packet) {
		if (channel == null) {
			writing = false;
			return;
		}

		writing = true;

		channel.writeAndFlush(packet).addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				writing = false;
				if (!future.isSuccess()) {
					exceptionCaught(null, future.cause());
				} else {
					getProtocol().onPacketSent(new PacketSentEvent(TcpClient.this, packet));
				}
			}
		});
		if (packet.isPriorityPacket()) {
			while (writing) {
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		if (disconnected) {
			ctx.channel().close().syncUninterruptibly();
			return;
		}
		channel = ctx.channel();
		disconnected = false;
		getProtocol().onConnect(new ConnectedEvent(this));
		new PacketHandleThread().start();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		disconnect("Connection closed (inactive).");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		writing = false;
		if (!disconnected) {
			if (cause instanceof TimeoutException) {
				getProtocol().onTimeout(new TimeoutEvent(TcpClient.this, cause instanceof ReadTimeoutException ? TimeoutType.READ : TimeoutType.WRITE));
				disconnect((cause instanceof ReadTimeoutException ? "Read" : "Write") + " timed out.");
			} else if (cause instanceof ConnectTimeoutException) {
				getProtocol().onTimeout(new TimeoutEvent(this, TimeoutType.CONNECT));
				disconnect("Connection timed out.");
			} else {
				cause.printStackTrace();
				disconnect("Internal network exception: " + cause.toString());
			}
		}
		disconnected = true;
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, ReadablePacket packet) throws Exception {
		if (!packet.isPriorityPacket()) { // already fired oio event for it
			packets.add(packet);
		} else {
			System.out.println("Ignoring " + packet.getClass().getCanonicalName());
		}
	}

	@Override
	public boolean isConnected() {
		return (channel != null) && channel.isOpen() && !disconnected;
	}

	private class PacketHandleThread extends Thread {

		public PacketHandleThread() {
			super(getSession().getUserID() + " TCPClient PacketHandler");
		}

		@Override
		public void run() {
			try {
				while (!disconnected) {
					while (packets.size() > 0) {
						getProtocol().onPacketReceived(new PacketReceivedEvent(TcpClient.this, packets.remove(0)));
					}
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (Throwable t) {
				try {
					exceptionCaught(null, t);
				} catch (Exception e) {
					System.err.println("Exception while handling exception!");
					e.printStackTrace();
				}
			}
		}
	}
}