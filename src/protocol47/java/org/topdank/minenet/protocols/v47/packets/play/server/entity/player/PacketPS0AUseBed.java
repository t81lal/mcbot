package org.topdank.minenet.protocols.v47.packets.play.server.entity.player;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS0AUseBed implements IdentifiableReadablePacket {

	private int entityId;
	private BlockLocation location;

	public PacketPS0AUseBed() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		location = Protocol47DataHelper.decodePosition(in);
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	public int getEntityId() {
		return entityId;
	}

	public BlockLocation getLocation() {
		return location;
	}

	@Override
	public int getId() {
		return 0x0A;
	}
}