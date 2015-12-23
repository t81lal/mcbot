package org.topdank.bot.net.io.bytebuf;

import java.io.IOException;
import java.util.UUID;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.io.WriteableOutput;

import io.netty.buffer.ByteBuf;

public class ByteBufReadWriteInOut implements ReadableInput, WriteableOutput {

	private final ByteBuf buf;

	public ByteBufReadWriteInOut(ByteBuf buf) {
		this.buf = buf;
	}

	@Override
	public boolean readBoolean() throws IOException {
		return buf.readBoolean();
	}

	@Override
	public byte readByte() throws IOException {
		return buf.readByte();
	}

	@Override
	public int readUnsignedByte() throws IOException {
		return buf.readUnsignedByte();
	}

	@Override
	public short readShort() throws IOException {
		return buf.readShort();
	}

	@Override
	public int readUnsignedShort() throws IOException {
		return buf.readUnsignedShort();
	}

	@Override
	public char readChar() throws IOException {
		return buf.readChar();
	}

	@Override
	public int readInt() throws IOException {
		return buf.readInt();
	}

	@Override
	public int readVarInt() throws IOException {
		int value = 0;
		int size = 0;
		int b;
		while (((b = readByte()) & 0x80) == 0x80) {
			value |= (b & 0x7F) << (size++ * 7);
			if (size > 5) {
				throw new IOException("VarInt too long. Hehe that's punny.");
			}
		}
		return value | ((b & 0x7F) << (size * 7));
	}

	@Override
	public long readLong() throws IOException {
		return buf.readLong();
	}

	@Override
	public long readVarLong() throws IOException {
		int value = 0;
		int size = 0;
		int b;
		while (((b = readByte()) & 0x80) == 0x80) {
			value |= (b & 0x7F) << (size++ * 7);
			if (size > 10) {
				throw new IOException("VarLong too long. That's what she said.");
			}
		}
		return value | ((b & 0x7F) << (size * 7));
	}

	@Override
	public float readFloat() throws IOException {
		return buf.readFloat();
	}

	@Override
	public double readDouble() throws IOException {
		return buf.readDouble();
	}

	@Override
	public byte[] readPrefixedBytes() throws IOException {
		short length = buf.readShort();
		return this.readBytes(length);
	}

	@Override
	public byte[] readBytes(int length) throws IOException {
		if (length < 0) {
			throw new IllegalArgumentException("Array cannot have length less than 0.");
		}
		byte b[] = new byte[length];
		buf.readBytes(b);
		return b;
	}

	@Override
	public int readBytes(byte[] b) throws IOException {
		return this.readBytes(b, 0, b.length);
	}

	@Override
	public int readBytes(byte[] b, int offset, int length) throws IOException {
		int readable = buf.readableBytes();
		if (readable <= 0) {
			return -1;
		}
		if (readable < length) {
			length = readable;
		}
		buf.readBytes(b, offset, length);
		return length;
	}

	@Override
	public String readString() throws IOException {
		int length = readVarInt();
		byte bytes[] = this.readBytes(length);
		return new String(bytes, "UTF-8");
	}

	@Override
	public UUID readUUID() throws IOException {
		return new UUID(readLong(), readLong());
	}

	@Override
	public int written() throws IOException {
		return buf.readableBytes();
	}

	@Override
	public void close() throws IOException {
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

	public int getWriterIndex() {
		return buf.writerIndex();
	}

	public void setWriterIndex(int i) {
		buf.writerIndex(i);
	}

	@Override
	public String readJagexString() throws IOException {
		throw new IOException("Unsupposed operation.");
	}

	@Override
	public int readerIndex() throws IOException {
		return buf.readerIndex();
	}

	@Override
	public void readerIndex(int i) {
		buf.readerIndex(i);
	}

	@Override
	public void initBitAccess() throws IOException {
		throw new IOException("Unsupposed operation.");
	}

	@Override
	public int readBits(int amt) throws IOException {
		throw new IOException("Unsupposed operation.");
	}

	@Override
	public void finishBitAccess() throws IOException {
		throw new IOException("Unsupposed operation.");
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