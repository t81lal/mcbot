package org.topdank.minenet.protocols.v47.packets.login.client;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;

public class PacketLC01EncryptionResponse implements IdentifiableWriteablePacket {

	private byte[] sharedKey;
	private byte[] verifyToken;

	public PacketLC01EncryptionResponse(SecretKey secretKey, PublicKey publicKey, byte[] verifyToken) {
		sharedKey = encrypt(publicKey, secretKey.getEncoded());
		this.verifyToken = encrypt(publicKey, verifyToken);
	}

	private byte[] encrypt(Key key, byte[] data) {
		try {
			Cipher cipher = Cipher.getInstance(key.getAlgorithm());
			cipher.init(1, key);
			return cipher.doFinal(data);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeVarInt(sharedKey.length);
		out.writeBytes(sharedKey);
		out.writeVarInt(verifyToken.length);
		out.writeBytes(verifyToken);
	}

	@Override
	public boolean isPriorityPacket() {
		return true;
	}

	@Override
	public int getId() {
		return 0x01;
	}
}