package org.topdank.minenet.protocols.v47.packets.play.server.general;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS46SetCompression implements IdentifiableReadablePacket {

	private int threshold;

	@Override
	public void read(ReadableInput input) throws IOException {
		threshold = input.readVarInt();
	}

	public int getThreshold() {
		return threshold;
	}

	@Override
	public boolean isPriorityPacket() {
		return true;
	}

	@Override
	public int getId() {
		return 0x46;
	}
}