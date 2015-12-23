package org.topdank.minenet.protocols.v47.packets.play.server.entity.movement;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS18EntityTeleport implements IdentifiableReadablePacket {

	private int entityId;
	private double x;
	private double y;
	private double z;
	private float yaw;
	private float pitch;
	private boolean onGround;

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		x = in.readInt() / 32D;
		y = in.readInt() / 32D;
		z = in.readInt() / 32D;
		yaw = (in.readByte() * 360) / 256f;
		pitch = (in.readByte() * 360) / 256f;
		onGround = in.readBoolean();
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

	public float getYaw() {
		return yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public boolean isOnGround() {
		return onGround;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x18;
	}
}