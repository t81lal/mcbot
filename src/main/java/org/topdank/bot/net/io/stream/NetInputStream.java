package org.topdank.bot.net.io.stream;

import java.io.IOException;
import java.io.InputStream;

import org.topdank.bot.net.io.ReadableInput;

public class NetInputStream extends InputStream {
	
	private final ReadableInput in;
	private final byte firstByte;
	private boolean readFirst;
	
	public NetInputStream(ReadableInput in, byte firstByte) {
		this.in = in;
		this.firstByte = firstByte;
	}
	
	@Override
	public int read() throws IOException {
		if (!readFirst) {
			readFirst = true;
			return firstByte;
		} else {
			return in.readUnsignedByte();
		}
	}
}