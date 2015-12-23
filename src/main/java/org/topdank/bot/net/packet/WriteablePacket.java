package org.topdank.bot.net.packet;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;

public interface WriteablePacket extends Packet {

	void write(WriteableOutput out) throws IOException;

	@Override
	boolean isPriorityPacket();
}