package org.topdank.minenet.protocols.v47.packets.play.server.entity;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS0ESpawnObject implements IdentifiableReadablePacket {

	private int entityId;
	private byte typeId;
	private double x;
	private double y;
	private double z;
	private float pitch;
	private float yaw;
	private int data;
	private double motX;
	private double motY;
	private double motZ;

	public PacketPS0ESpawnObject() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		typeId = in.readByte();
		x = in.readInt() / 32D;
		y = in.readInt() / 32D;
		z = in.readInt() / 32D;
		pitch = (in.readByte() * 360) / 256f;
		yaw = (in.readByte() * 360) / 256f;
		data = in.readInt();
		if (data > 0) {
			motX = in.readShort() / 8000D;
			motY = in.readShort() / 8000D;
			motZ = in.readShort() / 8000D;
		}
	}

	public int getEntityId() {
		return entityId;
	}

	public byte getTypeId() {
		return typeId;
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

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public int getData() {
		return data;
	}

	public double getMotX() {
		return motX;
	}

	public double getMotY() {
		return motY;
	}

	public double getMotZ() {
		return motZ;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x0E;
	}
}