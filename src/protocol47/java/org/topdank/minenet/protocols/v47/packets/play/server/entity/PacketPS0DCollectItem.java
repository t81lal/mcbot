package org.topdank.minenet.protocols.v47.packets.play.server.entity;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS0DCollectItem implements IdentifiableReadablePacket {

	private int collectedId;
	private int collectorId;

	public PacketPS0DCollectItem() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		collectedId = in.readVarInt();
		collectorId = in.readVarInt();
	}

	/**
	 * Get the id of the collected entity
	 * 
	 * @return id
	 */
	public int getCollectedId() {
		return collectedId;
	}

	/**
	 * Get the id of the entity that collected
	 * 
	 * @return id
	 */
	public int getCollectorId() {
		return collectorId;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x0D;
	}
}