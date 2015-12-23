package org.topdank.minenet.protocols.v47.packets.play.server.entity;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS1DEntityEffect implements IdentifiableReadablePacket {

	public PacketPS1DEntityEffect() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		in.readVarInt();
		in.readByte();
		in.readByte();
		in.readVarInt();
		in.readBoolean();
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x1D;
	}
}