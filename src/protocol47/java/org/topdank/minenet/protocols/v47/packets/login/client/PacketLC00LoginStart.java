package org.topdank.minenet.protocols.v47.packets.login.client;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;

public class PacketLC00LoginStart implements IdentifiableWriteablePacket {

	private String name;

	public PacketLC00LoginStart(String name) {
		this.name = name;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeString(name);
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