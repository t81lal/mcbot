package org.topdank.minenet.protocols.v47.packets.login.server;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketLS01EncryptionRequest implements IdentifiableReadablePacket {

	private String serverId;
	private PublicKey publicKey;
	private byte[] verifyToken;

	// called via reflection
	public PacketLS01EncryptionRequest() {
	}

	public String getServerId() {
		return serverId;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public byte[] getVerifyToken() {
		return verifyToken;
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		serverId = in.readString();
		byte[] bytes = in.readBytes(in.readVarInt());
		try {
			publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bytes));
		} catch (GeneralSecurityException e) {
			throw new IOException("Could not decrypt public key.", e);
		}

		verifyToken = in.readBytes(in.readVarInt());
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