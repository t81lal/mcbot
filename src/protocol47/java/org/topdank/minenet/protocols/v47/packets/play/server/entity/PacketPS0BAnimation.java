package org.topdank.minenet.protocols.v47.packets.play.server.entity;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS0BAnimation implements IdentifiableReadablePacket {

	private int entityId;
	private int animation;

	public PacketPS0BAnimation() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		animation = in.readUnsignedByte();
	}

	public int getEntityId() {
		return entityId;
	}

	public int getAnimation() {
		return animation;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x0B;
	}
}