package org.topdank.minenet.protocols.v47.packets.play;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableReadableWriteablePacket;

public class PacketPSC00KeepAlive implements IdentifiableReadableWriteablePacket {

	private int rId;

	public PacketPSC00KeepAlive() {

	}

	public PacketPSC00KeepAlive(int rId) {
		this.rId = rId;
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		rId = in.readVarInt();
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeVarInt(rId);
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