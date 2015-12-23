package org.topdank.minenet.protocols.v47.packets.handshake.client;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;

public class PacketHC00Handshake implements IdentifiableWriteablePacket {

	private int protocolVersion;
	private String hostname;
	private int port;
	private int state;

	public PacketHC00Handshake(int protocolVersion, String hostname, int port, int state) {
		this.protocolVersion = protocolVersion;
		this.hostname = hostname;
		this.port = port;
		this.state = state;
	}

	public int getProtocolVersion() {
		return protocolVersion;
	}

	public String getHostname() {
		return hostname;
	}

	public int getPort() {
		return port;
	}

	public int getState() {
		return state;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeVarInt(protocolVersion);
		out.writeString(hostname);
		out.writeShort(port);
		out.writeVarInt(state);
	}

	@Override
	public boolean isPriorityPacket() {
		return true;
	}

	@Override
	public int getId() {
		return 0x00;
	}
}