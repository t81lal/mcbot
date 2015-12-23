package org.topdank.minenet.protocols.v47.packets.play.server.entity.movement;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS12EntityVelocity implements IdentifiableReadablePacket {

	private int entityId;
	private double motX;
	private double motY;
	private double motZ;

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		motX = in.readShort() / 8000D;
		motY = in.readShort() / 8000D;
		motZ = in.readShort() / 8000D;
	}

	public int getEntityId() {
		return entityId;
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

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x12;
	}

	@Override
	public String toString() {
		return "PacketPS12EntityVelocity [id=" + Integer.toHexString(getId()) + ", entityId=" + entityId + ", motX=" + motX + ", motY=" + motY + ", motZ=" + motZ + "]";
	}
}