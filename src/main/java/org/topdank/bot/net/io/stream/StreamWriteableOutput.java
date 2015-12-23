package org.topdank.bot.net.io.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.topdank.bot.net.io.WriteableOutput;

/**
 * A WriteableOutput implementation using an OutputStream as
 * a backend.
 *
 * @author Bibl
 */
public class StreamWriteableOutput implements WriteableOutput {

	private final OutputStream out;
	private int written;

	public StreamWriteableOutput(OutputStream out) {
		this.out = out;
	}

	@Override
	public void writeBoolean(boolean b) throws IOException {
		writeByte(b ? 1 : 0);
	}

	@Override
	public void writeByte(int b) throws IOException {
		out.write(b);
		written++;
	}

	@Override
	public void writeShort(int s) throws IOException {
		writeByte((byte) ((s >>> 8) & 0xFF));
		writeByte((byte) ((s >>> 0) & 0xFF));
	}

	@Override
	public void writeChar(int c) throws IOException {
		writeByte((byte) ((c >>> 8) & 0xFF));
		writeByte((byte) ((c >>> 0) & 0xFF));
	}

	@Override
	public void writeInt(int i) throws IOException {
		writeByte((byte) ((i >>> 24) & 0xFF));
		writeByte((byte) ((i >>> 16) & 0xFF));
		writeByte((byte) ((i >>> 8) & 0xFF));
		writeByte((byte) ((i >>> 0) & 0xFF));
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
		writeByte((byte) (l >>> 56));
		writeByte((byte) (l >>> 48));
		writeByte((byte) (l >>> 40));
		writeByte((byte) (l >>> 32));
		writeByte((byte) (l >>> 24));
		writeByte((byte) (l >>> 16));
		writeByte((byte) (l >>> 8));
		writeByte((byte) (l >>> 0));
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
		writeInt(Float.floatToIntBits(f));
	}

	@Override
	public void writeDouble(double d) throws IOException {
		writeLong(Double.doubleToLongBits(d));
	}

	@Override
	public void writePrefixedBytes(byte b[]) throws IOException {
		writeShort(b.length);
		this.writeBytes(b);
	}

	@Override
	public void writeBytes(byte b[]) throws IOException {
		this.writeBytes(b, b.length);
	}

	@Override
	public void writeBytes(byte b[], int length) throws IOException {
		out.write(b, 0, length);
		written += length;
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
		out.flush();
		written = 0;
	}

	@Override
	public void close() throws IOException {
		out.flush();
		out.close();
		written = 0;
	}

	public OutputStream getUnderlyingStream() {
		return out;
	}

	@Override
	public int written() {
		return written;
	}

	@Override
	public int writerIndex() {
		return written;
	}

	@Override
	public void writerIndex(int pos) {
		throw new UnsupportedOperationException();
	}
}