package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.world.pos.BlockLocation;

public class PacketPS2CSpawnGlobalEntity implements IdentifiableReadablePacket {

	private int entityId;
	private byte type;
	private BlockLocation loc;

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		type = in.readByte();
		loc = new BlockLocation(in.readInt(), in.readInt(), in.readInt());
	}

	public int getEntityId() {
		return entityId;
	}

	public byte getType() {
		return type;
	}

	public BlockLocation getLoc() {
		return loc;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x2C;
	}
}