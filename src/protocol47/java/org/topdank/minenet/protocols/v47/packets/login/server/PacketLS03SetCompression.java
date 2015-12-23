package org.topdank.minenet.protocols.v47.packets.login.server;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableReadableWriteablePacket;

public class PacketLS03SetCompression implements IdentifiableReadableWriteablePacket {

	private int threshold;

	private PacketLS03SetCompression() {

	}

	public int getThreshold() {
		return threshold;
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		threshold = in.readVarInt();
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeVarInt(threshold);
	}

	@Override
	public boolean isPriorityPacket() {
		return true;
	}

	@Override
	public int getId() {
		return 0x03;
	}
}