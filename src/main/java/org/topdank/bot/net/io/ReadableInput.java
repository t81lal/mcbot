package org.topdank.bot.net.io;

import java.io.IOException;
import java.util.UUID;

/**
 * Generic readable layer.
 */
public interface ReadableInput {

	/**
	 * Reads the next boolean.
	 *
	 * @return The next boolean.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	boolean readBoolean() throws IOException;

	/**
	 * Reads the next byte.
	 *
	 * @return The next byte.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	byte readByte() throws IOException;

	/**
	 * Reads the next unsigned byte.
	 *
	 * @return The next unsigned byte.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	int readUnsignedByte() throws IOException;

	/**
	 * Reads the next short.
	 *
	 * @return The next short.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	short readShort() throws IOException;

	/**
	 * Reads the next unsigned short.
	 *
	 * @return The next unsigned short.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	int readUnsignedShort() throws IOException;

	/**
	 * Reads the next char.
	 *
	 * @return The next char.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	char readChar() throws IOException;

	/**
	 * Reads the next integer.
	 *
	 * @return The next integer.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	int readInt() throws IOException;

	/**
	 * Reads the next varint. A varint is a form of integer
	 * where only necessary bytes are written. This is done
	 * to save bandwidth.
	 *
	 * @return The next varint.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	int readVarInt() throws IOException;

	/**
	 * Reads the next long.
	 *
	 * @return The next long.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	long readLong() throws IOException;

	/**
	 * Reads the next varlong. A varlong is a form of long
	 * where only necessary bytes are written. This is done
	 * to save bandwidth.
	 *
	 * @return The next varlong.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	long readVarLong() throws IOException;

	/**
	 * Reads the next float.
	 *
	 * @return The next float.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	float readFloat() throws IOException;

	/**
	 * Reads the next double.
	 *
	 * @return The next double.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	double readDouble() throws IOException;

	/**
	 * Reads the next byte array, getting the length from a
	 * prefixed length value.
	 *
	 * @return The next byte array.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	byte[] readPrefixedBytes() throws IOException;

	/**
	 * Reads the next byte array.
	 *
	 * @param length The length of the byte array.
	 * @return The next byte array.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	byte[] readBytes(int length) throws IOException;

	/**
	 * Reads as much data as possible into the given byte
	 * array.
	 *
	 * @param b Byte array to read to.
	 * @return The amount of bytes read, or -1 if no bytes
	 *         could be read.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	int readBytes(byte b[]) throws IOException;

	/**
	 * Reads the given amount of bytes into the given array
	 * at the given offset.
	 *
	 * @param b Byte array to read to.
	 * @param offset Offset of the array to read to.
	 * @param length Length of bytes to read.
	 * @return The amount of bytes read, or -1 if no bytes
	 *         could be read.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	int readBytes(byte b[], int offset, int length) throws IOException;

	/**
	 * Reads the next string.
	 *
	 * @return The next string.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	String readString() throws IOException;

	/**
	 * Reads a Jagex 317 style String. <br>
	 * The implementation for subclasses should be
	 * practically the same as the String is terminated by a
	 * byte with the value of 10, with all of the previous
	 * read bytes being the raw String.
	 *
	 * @return The next Jagex string.
	 * @throws IOException If an I/O error occurs.
	 */
	String readJagexString() throws IOException;

	/**
	 * Reads the next UUID.
	 *
	 * @return The next UUID.
	 * @throws java.io.IOException If an I/O error occurs.
	 */
	UUID readUUID() throws IOException;

	/**
	 * Gets the number of available bytes.
	 *
	 * @return The number of available bytes.
	 * @throws IOException If an I/O error occurs.
	 */
	int written() throws IOException;

	/**
	 * Gets the position of the reader.
	 *
	 * @return The position of the reader.
	 * @throws IOException If an I/O error occurs.
	 */
	int readerIndex() throws IOException;

	/**
	 * Sets the position of the reader head.
	 *
	 * @param pos The position of the reader.
	 * @throws IOException If an I/O error occurs.
	 */
	void readerIndex(int pos) throws IOException;

	/**
	 * Enables bit reading mode so that the underlying
	 * buffer is able to be read bit by bit rather than byte
	 * by byte.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	void initBitAccess() throws IOException;

	/**
	 * Reads bits from the underlying buffer.
	 *
	 * @param amt The amount of bits to read.
	 * @return The read bits.
	 * @throws IOException If an I/O error occurs.
	 */
	int readBits(int amt) throws IOException;

	/**
	 * Stops reading in bit mode.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	void finishBitAccess() throws IOException;

	/**
	 * Closes the input, as per convention.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	void close() throws IOException;
}