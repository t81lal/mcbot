package org.topdank.bot.net.packet;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;

public interface ReadablePacket extends Packet {

	void read(ReadableInput in) throws IOException;

	@Override
	boolean isPriorityPacket();

	@Override
	boolean isIdentifiable();
}