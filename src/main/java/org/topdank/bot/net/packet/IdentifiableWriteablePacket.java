package org.topdank.bot.net.packet;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;

public interface IdentifiableWriteablePacket extends IdentifiablePacket, WriteablePacket {

	@Override
	void write(WriteableOutput out) throws IOException;

	@Override
	boolean isPriorityPacket();

	@Override
	int getId();

	@Override
	default public boolean isIdentifiable() {
		return true;
	}
}