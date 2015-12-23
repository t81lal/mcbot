package org.topdank.bot.net.io.stream;

import java.io.IOException;
import java.io.OutputStream;

import org.topdank.bot.net.io.WriteableOutput;

public class NetOutputStream extends OutputStream {
	
	private final WriteableOutput out;
	
	public NetOutputStream(WriteableOutput out) {
		this.out = out;
	}
	
	@Override
	public void write(int b) throws IOException {
		out.writeByte(b);
	}
}