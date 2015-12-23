package org.topdank.bot.net;

import java.io.IOException;

import org.topdank.bot.Protocol;
import org.topdank.bot.auth.Session;
import org.topdank.bot.eventbus.Event;
import org.topdank.bot.eventbus.EventBus;
import org.topdank.bot.net.packet.ReadablePacket;
import org.topdank.bot.net.packet.WriteablePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public abstract class Client<T extends Session> extends SimpleChannelInboundHandler<ReadablePacket> {

	private final T session;
	private final String host;
	private final int port;
	private final Protocol protocol;
	private final EventBus eventBus;
	private int compressionLevel;

	public Client(T session, String host, int port, Protocol protocol, EventBus eventBus) throws IOException {
		this.session = session;
		this.host = host;
		this.port = port;
		this.protocol = protocol;
		this.eventBus = eventBus;
		
		compressionLevel = -1;
	}

	public Session getSession() {
		return session;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public void setCompression(int ct) {
		compressionLevel = ct;
	}

	public int getCompression() {
		return compressionLevel;
	}

	public Protocol getProtocol() {
		return protocol;
	}
	
	public void dispatchEvent(Event e) {
		eventBus.dispatch(e);
	}
	
	public EventBus getEventBus() {
		return eventBus;
	}

	public abstract void connect() throws IOException;

	public abstract void connect(boolean wait) throws IOException;

	public abstract void disconnect(String reason) throws IOException;

	public abstract boolean isConnected();

	public abstract void send(WriteablePacket packet);
	
	@Override
	public abstract void channelActive(ChannelHandlerContext ctx) throws Exception;

	@Override
	public abstract void channelInactive(ChannelHandlerContext ctx) throws Exception;

	@Override
	public abstract void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception;

	@Override
	protected abstract void messageReceived(ChannelHandlerContext ctx, ReadablePacket packet) throws Exception;
}