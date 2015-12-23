package org.topdank.minenet.protocols.v47.packets.play.client.entity;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;

public class PacketPC0BEntityAction implements IdentifiableWriteablePacket {

	private int entityId;
	private int actionId;
	private int jumpBoost;

	public PacketPC0BEntityAction(int entityId, int actionId, int jumpBoost) {
		this.entityId = entityId;
		this.actionId = actionId;
		this.jumpBoost = jumpBoost;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeVarInt(entityId);
		out.writeVarInt(actionId);
		out.writeVarInt(jumpBoost);
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