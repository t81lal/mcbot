package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS47PlayerListHeaderFooter implements IdentifiableReadablePacket {

	public PacketPS47PlayerListHeaderFooter() {

	}

	@Override
	public void read(ReadableInput in) throws IOException { // TODO Auto-generated method stub
		in.readString();
		in.readString();
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x47;
	}
}