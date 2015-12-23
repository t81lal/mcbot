package org.topdank.minenet.protocols.v47.packets.play.server.entity;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS1ERemoveEntityEffect implements IdentifiableReadablePacket {

	private int entityId;
	private byte effectId;

	public PacketPS1ERemoveEntityEffect() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		effectId = in.readByte();
	}

	public int getEntityId() {
		return entityId;
	}

	public byte getEffectId() {
		return effectId;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x1E;
	}
}