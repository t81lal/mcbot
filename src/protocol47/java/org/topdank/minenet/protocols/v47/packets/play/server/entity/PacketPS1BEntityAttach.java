package org.topdank.minenet.protocols.v47.packets.play.server.entity;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS1BEntityAttach implements IdentifiableReadablePacket {

	private int entityId;
	private int vehicleId;
	private boolean isLeash;

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readInt();
		vehicleId = in.readInt();
		isLeash = in.readBoolean();
	}

	public int getEntityId() {
		return entityId;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public boolean isLeash() {
		return isLeash;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x1B;
	}
}