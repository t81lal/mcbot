package org.topdank.minenet.protocols.v47.packets.play.server.entity.movement;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS19EntityHeadLook implements IdentifiableReadablePacket {

	private int entityId;
	private float headYaw;

	public PacketPS19EntityHeadLook() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		headYaw = (in.readByte() * 360) / 256f;
	}

	public int getEntityId() {
		return entityId;
	}

	public float getHeadYaw() {
		return headYaw;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x19;
	}
}