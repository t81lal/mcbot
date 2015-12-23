package org.topdank.minenet.protocols.v47.packets.play.server.general;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS42CombatEvent implements IdentifiableReadablePacket {

	// 0 ENTER_COMBAT, 1 END_COMBAT, 2 ENTITY_DEAD
	private int eventType;
	private int duration;
	private int entityId;
	private int playerId;
	private String message;

	@Override
	public void read(ReadableInput in) throws IOException {
		eventType = in.readVarInt();
		if (eventType == 1) {
			duration = in.readVarInt();
			entityId = in.readInt();
		} else if (eventType == 2) {
			playerId = in.readVarInt();
			entityId = in.readInt();
			message = in.readString();
		}
	}

	public int getEventType() {
		return eventType;
	}

	public int getDuration() {
		return duration;
	}

	public int getEntityId() {
		return entityId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x42;
	}
}