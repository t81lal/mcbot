package org.topdank.minenet.protocols.v47.packets.play.client;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;

public class PacketPC19ResourcePackStatus implements IdentifiableWriteablePacket {

	private String hash;
	private int status;

	public PacketPC19ResourcePackStatus(String hash, int status) {
		this.hash = hash;
		this.status = status;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeString(hash);
		out.writeVarInt(status);
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0;
	}
}