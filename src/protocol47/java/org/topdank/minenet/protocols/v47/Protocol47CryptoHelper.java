package org.topdank.minenet.protocols.v47;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.PublicKey;

import javax.crypto.SecretKey;

import org.topdank.bot.net.packet.encryption.AESEncryptionProtocol;
import org.topdank.bot.net.packet.encryption.EncryptionProtocol;

public class Protocol47CryptoHelper {

	public static byte[] generateServerHash(String id, PublicKey publicKey, SecretKey secretKey) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			for (byte array[] : new byte[][] { id.getBytes("ISO_8859_1"), secretKey.getEncoded(), publicKey.getEncoded() }) {
				digest.update(array);
			}
			return digest.digest();
		} catch (GeneralSecurityException e) {
			throw new RuntimeException("Failed to encrypt data.", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("VM doesn't support ISO_8859_1 strings.", e);
		}
	}

	public static EncryptionProtocol createEncryption(Key key) {
		try {
			return new AESEncryptionProtocol(key);
		} catch (GeneralSecurityException e) {
			throw new RuntimeException("Failed to enable protocol encryption.", e);
		}
	}
}