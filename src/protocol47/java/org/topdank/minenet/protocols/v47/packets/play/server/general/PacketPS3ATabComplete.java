package org.topdank.minenet.protocols.v47.packets.play.server.general;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS3ATabComplete implements IdentifiableReadablePacket {

	private String[] matches;

	@Override
	public void read(ReadableInput in) throws IOException {
		int count = in.readVarInt();
		matches = new String[count];
		for (int i = 0; i < count; i++) {
			matches[i] = in.readString();
		}
	}

	public String[] getMatches() {
		return matches;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x3A;
	}
}