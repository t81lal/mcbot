package org.topdank.minenet.protocols.v47.packets.play.server.scoreboard;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS3BScoreboardObjective implements IdentifiableReadablePacket {

	@Override
	public void read(ReadableInput in) throws IOException {
		in.readString();
		byte b = in.readByte();
		if ((b == 0) || (b == 2)) {
			in.readString();
			in.readString();
		}
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x3B;
	}
}