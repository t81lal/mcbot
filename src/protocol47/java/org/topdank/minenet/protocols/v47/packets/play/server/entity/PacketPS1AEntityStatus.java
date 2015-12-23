package org.topdank.minenet.protocols.v47.packets.play.server.entity;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS1AEntityStatus implements IdentifiableReadablePacket {

	private int entityId;
	private int status;

	public PacketPS1AEntityStatus() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readInt();
		status = in.readByte();
	}

	public int getEntityId() {
		return entityId;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x1A;
	}
}