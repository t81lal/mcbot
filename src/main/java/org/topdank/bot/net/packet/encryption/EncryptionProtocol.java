package org.topdank.bot.net.packet.encryption;

/**
 * Packet encryption protocol.
 * @author Bibl
 */
public interface EncryptionProtocol {
	
	/**
	 * Gets the output size from decrypting.
	 *
	 * @param length Length of the data being decrypted.
	 */
	int calcDecryptedLength(int length);

	/**
	 * Gets the output size from encrypting.
	 *
	 * @param length Length of the data being encrypted.
	 */
	int calcEncryptLength(int length);

	/**
	 * Decrypts the given data.
	 *
	 * @param input Input data to decrypt.
	 * @param inputOffset Offset of the data to start decrypting at.
	 * @param inputLength Length of the data to be decrypted.
	 * @param output Array to output decrypted data to.
	 * @param outputOffset Offset of the output array to start at.
	 * @return The number of bytes stored in the output array.
	 */
	int decrypt(byte input[], int inputOffset, int inputLength, byte output[], int outputOffset) throws Exception;

	/**
	 * Encrypts the given data.
	 *
	 * @param input Input data to encrypt.
	 * @param inputOffset Offset of the data to start encrypting at.
	 * @param inputLength Length of the data to be encrypted.
	 * @param output Array to output encrypted data to.
	 * @param outputOffset Offset of the output array to start at.
	 * @return The number of bytes stored in the output array.
	 */
	int encrypt(byte input[], int inputOffset, int inputLength, byte output[], int outputOffset) throws Exception;
}