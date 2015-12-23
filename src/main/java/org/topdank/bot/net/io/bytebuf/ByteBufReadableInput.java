package org.topdank.bot.net.io.bytebuf;

import java.io.IOException;
import java.util.UUID;

import org.topdank.bot.net.io.ReadableInput;

import io.netty.buffer.ByteBuf;

public class ByteBufReadableInput implements ReadableInput {

	private final ByteBuf buf;

	public ByteBufReadableInput(ByteBuf buf) {
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
	public String readJagexString() throws IOException {
		throw new IOException("Unsupposed operation.");
	}

	@Override
	public int readerIndex() throws IOException {
		return buf.readerIndex();
	}

	@Override
	public void readerIndex(int pos) throws IOException {
		buf.readerIndex(pos);
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
}