package org.topdank.minenet.protocols.v47.packets.play.server.entity.movement;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public abstract class PacketPSBaseMove implements IdentifiableReadablePacket {

	protected int entityId;
	protected double moveX;
	protected double moveY;
	protected double moveZ;
	protected float yaw;
	protected float pitch;
	protected boolean onGround;

	protected boolean pos = false;
	protected boolean rot = false;

	public PacketPSBaseMove() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		if (pos) {
			moveX = in.readByte() / 32D;
			moveY = in.readByte() / 32D;
			moveZ = in.readByte() / 32D;
		}
		if (rot) {
			yaw = (in.readByte() * 360) / 256F;
			pitch = (in.readByte() * 360) / 256F;
		}
		onGround = in.readBoolean();
	}

	public int getEntityId() {
		return entityId;
	}

	public double getMovementX() {
		return moveX;
	}

	public double getMovementY() {
		return moveY;
	}

	public double getMovementZ() {
		return moveZ;
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
	public abstract int getId();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append("[id= ").append(Integer.toHexString(getId())).append(", ");
		if (pos) {
			sb.append("x= ").append(moveX).append(", ");
			sb.append("y= ").append(moveY).append(", ");
			sb.append("z= ").append(moveZ).append(", ");
		}
		if (rot) {
			sb.append("yaw= ").append(yaw).append(", ");
			sb.append("pitch= ").append(pitch).append(", ");
		}
		sb.append("onground= ").append(onGround).append("]");
		return sb.toString();
	}
}