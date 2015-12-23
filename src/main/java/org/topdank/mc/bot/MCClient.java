package org.topdank.mc.bot;

import java.io.IOException;

import org.topdank.bot.Protocol;
import org.topdank.bot.eventbus.EventBus;
import org.topdank.bot.eventbus.EventPriority;
import org.topdank.bot.eventbus.EventTarget;
import org.topdank.bot.net.Client;
import org.topdank.bot.net.TcpClient;
import org.topdank.bot.net.event.connection.ConnectedEvent;
import org.topdank.bot.net.event.packet.PacketReceivedEvent;
import org.topdank.mc.authyggdrasil.YggdrasilSession;
import org.topdank.mc.bot.api.BotContext;
import org.topdank.mc.util.Timer;

import io.netty.channel.ChannelHandlerContext;

public class MCClient extends TcpClient<YggdrasilSession> {

	protected BotContext context;

	public MCClient(YggdrasilSession session, String host, int port, Protocol protocol, EventBus bus) throws IOException {
		super(session, host, port, protocol, bus);

		if (!(protocol instanceof MCProtocol))
			throw new UnsupportedOperationException("MCClient requires a valid MCProtocol.");

		protocol.init(this);
		getEventBus().register(this);
	}

	public BotContext getBotContext() {
		return context;
	}

	public void setBotContext(BotContext context) {
		if (this.context == null) {
			this.context = context;
		}
	}

	@EventTarget(priority = EventPriority.HIGHEST)
	public void onRequestPacketSendEvent(RequestPacketSendEvent e) {
		Client<?> c = e.getClient();
		if (c.equals(this)) {
			send(e.getPacket());
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("MCClient.channelActive()");
		if (isDisconnected()) {
			ctx.channel().close().syncUninterruptibly();
			return;
		}
		setChannel(ctx.channel());
		setDisconnected(false);
		getProtocol().onConnect(new ConnectedEvent(this));
		new TimedPacketHandleThread().start();
	}

	private class TimedPacketHandleThread extends Thread {

		private final Timer timer = new Timer(20, 20);

		public TimedPacketHandleThread() {
			super(((YggdrasilSession) getSession()).getUsername() + " MCClient PacketHandler");
		}

		@Override
		public void run() {
			try {
				while (true) {
					timer.update();
					for (int i = 0; i < timer.getElapsedTicks(); i++) {
						while (packets.size() > 0) {
							getProtocol().onPacketReceived(new PacketReceivedEvent(MCClient.this, packets.remove(0)));
						}
						((MCProtocol) getProtocol()).onTick();
					}
					if (timer.getFPSCoolDown() > 0) {
						try {
							Thread.sleep(timer.getFPSCoolDown());
						} catch (InterruptedException exception) {
							exception.printStackTrace();
						}
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