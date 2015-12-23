package org.topdank.bot.net.packet;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;

public interface IdentifiableReadablePacket extends IdentifiablePacket, ReadablePacket {

	@Override
	void read(ReadableInput in) throws IOException;

	@Override
	boolean isPriorityPacket();

	@Override
	default public boolean isIdentifiable() {
		return true;
	}
}