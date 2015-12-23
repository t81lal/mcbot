package org.topdank.minenet.protocols.v47.packets.play.server.entity.player;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS11SpawnExpOrb implements IdentifiableReadablePacket {

	private int entityId;
	private double x;
	private double y;
	private double z;
	private short count;

	public PacketPS11SpawnExpOrb() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		x = in.readInt() / 32D;
		y = in.readInt() / 32D;
		z = in.readInt() / 32D;
		count = in.readShort();
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	public int getEntityId() {
		return entityId;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public short getCount() {
		return count;
	}

	@Override
	public int getId() {
		return 0x11;
	}
}