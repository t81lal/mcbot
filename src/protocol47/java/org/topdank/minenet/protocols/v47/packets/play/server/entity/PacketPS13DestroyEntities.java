package org.topdank.minenet.protocols.v47.packets.play.server.entity;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS13DestroyEntities implements IdentifiableReadablePacket {

	private int[] entityIds;

	@Override
	public void read(ReadableInput in) throws IOException {
		int len = in.readVarInt();
		entityIds = new int[len];
		for (int i = 0; i < len; i++) {
			entityIds[i] = in.readVarInt();
		}
	}

	public int[] getEntityIds() {
		return entityIds;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x13;
	}
}