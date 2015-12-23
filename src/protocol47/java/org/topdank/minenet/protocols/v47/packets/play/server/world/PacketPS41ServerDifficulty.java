package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS41ServerDifficulty implements IdentifiableReadablePacket {

	private int difficultId;

	@Override
	public void read(ReadableInput in) throws IOException {
		difficultId = in.readUnsignedByte();
	}

	public int getDifficultId() {
		return difficultId;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x41;
	}
}