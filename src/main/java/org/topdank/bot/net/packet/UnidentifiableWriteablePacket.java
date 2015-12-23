package org.topdank.bot.net.packet;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;

public abstract class UnidentifiableWriteablePacket implements WriteablePacket {

	public UnidentifiableWriteablePacket() {
	}

	@Override
	public abstract void write(WriteableOutput out) throws IOException;

	@Override
	public boolean isPriorityPacket() {
		return true;
	}

	@Override
	public final boolean isIdentifiable() {
		return false;
	}
}