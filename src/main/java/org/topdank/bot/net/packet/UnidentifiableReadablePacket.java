package org.topdank.bot.net.packet;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;

public class UnidentifiableReadablePacket implements ReadablePacket {

	private ReadableInput in;

	public UnidentifiableReadablePacket() {
	}

	@Override
	public final void read(ReadableInput in) throws IOException {
		if (this.in == null)
			this.in = in;
	}

	public ReadableInput in() {
		return in;
	}

	@Override
	public boolean isPriorityPacket() {
		return true;
	}

	@Override
	public final boolean isIdentifiable() {
		return false;
	}
}