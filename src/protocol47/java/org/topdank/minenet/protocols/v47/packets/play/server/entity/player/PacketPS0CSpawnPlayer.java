package org.topdank.minenet.protocols.v47.packets.play.server.entity.player;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS0CSpawnPlayer implements IdentifiableReadablePacket {

	private int entityId;
	private UUID uuid;
	private double x;
	private double y;
	private double z;
	private float yaw;
	private float pitch;
	private int currentItem;
	private Map<Integer, Object> metadata;

	public PacketPS0CSpawnPlayer() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		uuid = in.readUUID();
		x = in.readInt() / 32D;
		y = in.readInt() / 32D;
		z = in.readInt() / 32D;
		yaw = (in.readByte() * 360) / 256f;
		pitch = (in.readByte() * 360) / 256f;
		currentItem = in.readShort();
		metadata = Protocol47DataHelper.readEntityMetadata(in);
	}

	public int getEntityId() {
		return entityId;
	}

	public UUID getUUID() {
		return uuid;
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

	public int getCurrentItem() {
		return currentItem;
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
		return 0x0C;
	}
}