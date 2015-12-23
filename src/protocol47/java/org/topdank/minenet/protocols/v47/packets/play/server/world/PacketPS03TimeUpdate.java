package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS03TimeUpdate implements IdentifiableReadablePacket {

	private long worldAge;
	private long time;

	public PacketPS03TimeUpdate() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		worldAge = in.readLong();
		time = in.readLong();
	}

	public long getWorldAge() {
		return worldAge;
	}

	public long getTime() {
		return time;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x03;
	}
}