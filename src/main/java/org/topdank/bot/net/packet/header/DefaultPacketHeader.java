package org.topdank.bot.net.packet.header;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.io.WriteableOutput;

public class DefaultPacketHeader implements PacketHeader {
	
	@Override
	public boolean isLengthVariable() {
		return true;
	}
	
	@Override
	public int getLengthSize() {
		return 5;
	}
	
	@Override
	public int getLengthSize(int length) {
		return varIntLength(length);
	}
	
	@Override
	public int readLength(ReadableInput in, int available) throws IOException {
		return in.readVarInt();
	}
	
	@Override
	public void writeLength(WriteableOutput out, int length) throws IOException {
		out.writeVarInt(length);
	}
	
	@Override
	public int readPacketId(ReadableInput in) throws IOException {
		return in.readVarInt();
	}
	
	@Override
	public void writePacketId(WriteableOutput out, int packetId) throws IOException {
		out.writeVarInt(packetId);
	}
	
	private static int varIntLength(int i) {
		if ((i & -128) == 0) {
			return 1;
		} else if ((i & -16384) == 0) {
			return 2;
		} else if ((i & -2097152) == 0) {
			return 3;
		} else if ((i & -268435456) == 0) {
			return 4;
		} else {
			return 5;
		}
	}
}