package org.topdank.minenet.protocols.v47.packets.play.server.scoreboard;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS3DDisplayScoreboard implements IdentifiableReadablePacket {

	@Override
	public void read(ReadableInput in) throws IOException {
		in.readByte();
		in.readString();
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x3D;
	}
}