package org.topdank.minenet.protocols.v47.packets.play.server.general;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS37Statistics implements IdentifiableReadablePacket {

	public PacketPS37Statistics() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		int len = in.readVarInt();
		for (int i = 0; i < len; i++) {
			in.readString();// TODO: save?
		}
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x37;
	}
}