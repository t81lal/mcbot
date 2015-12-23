package org.topdank.minenet.protocols.v47.packets.play.client.entity;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;

public class PacketPC02UseEntity implements IdentifiableWriteablePacket {

	private int targetEntityId;
	// 0 = INTERACT, 1 = ATTACK, 2 = INTERACT_AT
	private int actionId;
	private float targetX;
	private float targetY;
	private float targetZ;

	public PacketPC02UseEntity(int targetEntityId, int actionId) {
		this.targetEntityId = targetEntityId;
		this.actionId = actionId;
	}

	public PacketPC02UseEntity(int targetEntityId, int actionId, float targetX, float targetY, float targetZ) {
		this.targetEntityId = targetEntityId;
		this.actionId = actionId;
		if (actionId != 2)
			throw new IllegalArgumentException("Cannot have target co ords with actionId != 2 UseEntity packet.");
		this.targetX = targetX;
		this.targetY = targetY;
		this.targetZ = targetZ;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeVarInt(targetEntityId);
		out.writeVarInt(actionId);
		if (actionId == 2) {// INTERACT AT
			out.writeFloat(targetX);
			out.writeFloat(targetY);
			out.writeFloat(targetZ);
		}
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x02;
	}
}