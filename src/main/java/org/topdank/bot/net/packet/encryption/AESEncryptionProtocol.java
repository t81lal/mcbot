package org.topdank.bot.net.packet.encryption;

import java.security.GeneralSecurityException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

/**
 * An encryption implementation using "AES/CFB8/NoPadding" encryption.
 */
public class AESEncryptionProtocol implements EncryptionProtocol {
	
	private final Cipher inCipher;
	private final Cipher outCipher;
	
	public AESEncryptionProtocol(Key key) throws GeneralSecurityException {
		inCipher = Cipher.getInstance("AES/CFB8/NoPadding");
		inCipher.init(2, key, new IvParameterSpec(key.getEncoded()));
		outCipher = Cipher.getInstance("AES/CFB8/NoPadding");
		outCipher.init(1, key, new IvParameterSpec(key.getEncoded()));
	}
	
	@Override
	public int calcDecryptedLength(int length) {
		return inCipher.getOutputSize(length);
	}
	
	@Override
	public int calcEncryptLength(int length) {
		return outCipher.getOutputSize(length);
	}
	
	@Override
	public int decrypt(byte[] input, int inputOffset, int inputLength, byte[] output, int outputOffset) throws Exception {
		return inCipher.update(input, inputOffset, inputLength, output, outputOffset);
	}
	
	@Override
	public int encrypt(byte[] input, int inputOffset, int inputLength, byte[] output, int outputOffset) throws Exception {
		return outCipher.update(input, inputOffset, inputLength, output, outputOffset);
	}
}