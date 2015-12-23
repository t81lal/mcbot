package org.topdank.bot.net.io.bytebuf;

import java.io.IOException;
import java.util.UUID;

import org.topdank.bot.net.io.WriteableOutput;

import io.netty.buffer.ByteBuf;

public class ByteBufWriteableOutput implements WriteableOutput {

	private final ByteBuf buf;

	public ByteBufWriteableOutput(ByteBuf buf) {
		this.buf = buf;
	}

	@Override
	public void writeBoolean(boolean b) throws IOException {
		buf.writeBoolean(b);
	}

	@Override
	public void writeByte(int b) throws IOException {
		buf.writeByte(b);
	}

	@Override
	public void writeShort(int s) throws IOException {
		buf.writeShort(s);
	}

	@Override
	public void writeChar(int c) throws IOException {
		buf.writeChar(c);
	}

	@Override
	public void writeInt(int i) throws IOException {
		buf.writeInt(i);
	}

	@Override
	public void writeVarInt(int i) throws IOException {
		while ((i & ~0x7F) != 0) {
			writeByte((i & 0x7F) | 0x80);
			i >>>= 7;
		}
		writeByte(i);
	}

	@Override
	public void writeLong(long l) throws IOException {
		buf.writeLong(l);
	}

	@Override
	public void writeVarLong(long l) throws IOException {
		while ((l & ~0x7F) != 0) {
			writeByte((int) (l & 0x7F) | 0x80);
			l >>>= 7;
		}
		writeByte((int) l);
	}

	@Override
	public void writeFloat(float f) throws IOException {
		buf.writeFloat(f);
	}

	@Override
	public void writeDouble(double d) throws IOException {
		buf.writeDouble(d);
	}

	@Override
	public void writePrefixedBytes(byte b[]) throws IOException {
		buf.writeShort(b.length);
		buf.writeBytes(b);
	}

	@Override
	public void writeBytes(byte b[]) throws IOException {
		buf.writeBytes(b);
	}

	@Override
	public void writeBytes(byte b[], int length) throws IOException {
		buf.writeBytes(b, 0, length);
	}

	@Override
	public void writeString(String s) throws IOException {
		if (s == null) {
			throw new IllegalArgumentException("String cannot be null!");
		}
		byte[] bytes = s.getBytes("UTF-8");
		if (bytes.length > 32767) {
			throw new IOException("String too big (was " + s.length() + " bytes encoded, max " + 32767 + ")");
		} else {
			writeVarInt(bytes.length);
			this.writeBytes(bytes);
		}
	}

	@Override
	public void writeUUID(UUID uuid) throws IOException {
		writeLong(uuid.getMostSignificantBits());
		writeLong(uuid.getLeastSignificantBits());
	}

	@Override
	public void flush() throws IOException {
	}

	@Override
	public void close() throws IOException {
	}

	@Override
	public int written() throws IOException {
		return buf.writerIndex();
	}

	@Override
	public int writerIndex() {
		return buf.writerIndex();
	}

	@Override
	public void writerIndex(int pos) {
		buf.writerIndex(pos);
	}
}