package org.topdank.minenet.protocols.v47.packets.play.server.entity;

import java.io.IOException;
import java.util.Map;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS0FSpawnMob implements IdentifiableReadablePacket {

	private int entityId;
	private int typeId;
	private double x;
	private double y;
	private double z;
	private float pitch;
	private float yaw;
	private float headYaw;
	private double motX;
	private double motY;
	private double motZ;
	private Map<Integer, Object> metadata;

	public PacketPS0FSpawnMob() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		typeId = in.readUnsignedByte();
		x = in.readInt() / 32D;
		y = in.readInt() / 32D;
		z = in.readInt() / 32D;
		yaw = (in.readByte() * 360) / 256f;
		pitch = (in.readByte() * 360) / 256f;
		headYaw = (in.readByte() * 360) / 256f;
		motX = in.readShort() / 8000D;
		motY = in.readShort() / 8000D;
		motZ = in.readShort() / 8000D;
		metadata = Protocol47DataHelper.readEntityMetadata(in);
	}

	public int getEntityId() {
		return entityId;
	}

	public int getTypeId() {
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

	public float getYaw() {
		return yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public float getHeadYaw() {
		return headYaw;
	}

	public double getMotionX() {
		return motX;
	}

	public double getMotionY() {
		return motY;
	}

	public double getMotionZ() {
		return motZ;
	}

	public Map<Integer, Object> getMetadata() {
		return metadata;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x0F;
	}

	@Override
	public String toString() {
		return "PacketPS0FSpawnMob [entityId=" + entityId + ", typeId=" + typeId + ", x=" + x + ", y=" + y + ", z=" + z + ", pitch=" + pitch + ", yaw=" + yaw + ", headYaw=" + headYaw + ", motX="
				+ motX + ", motY=" + motY + ", motZ=" + motZ + ", metadata=" + metadata + "]";
	}
}