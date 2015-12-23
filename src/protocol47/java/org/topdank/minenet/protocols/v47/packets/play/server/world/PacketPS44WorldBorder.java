package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS44WorldBorder implements IdentifiableReadablePacket {

	// 0 (SET_SIZE), 1 (LERP_SIZE) , 2 (SET_CENTER) , 3 (INITIALIZE), 4 (SET_WARNING_TIME), 5 (SET_WARNING_BLOCKS)
	private int actionId;

	// 0
	private double radius;
	// 1, 3
	private double oldRadius, newRadius;
	private long speed;
	// 2, 3
	private double x, z;
	// 3
	private int portalTeleportBoundary;
	// 3, 4, 5
	private int warningTime, warningBlocks;

	@Override
	public void read(ReadableInput in) throws IOException {
		actionId = in.readVarInt();
		if (actionId == 0) {
			radius = in.readDouble();
		} else if (actionId == 1) {
			oldRadius = in.readDouble();
			newRadius = in.readDouble();
			speed = in.readVarLong();
		} else if (actionId == 2) {
			x = in.readDouble();
			z = in.readDouble();
		} else if (actionId == 3) {
			x = in.readDouble();
			z = in.readDouble();
			oldRadius = in.readDouble();
			newRadius = in.readDouble();
			speed = in.readVarLong();
			portalTeleportBoundary = in.readVarInt();
			warningTime = in.readVarInt();
			warningBlocks = in.readVarInt();
		} else if (actionId == 4) {
			warningTime = in.readVarInt();
		} else if (actionId == 5) {
			warningBlocks = in.readVarInt();
		}
	}

	public int getActionId() {
		return actionId;
	}

	public double getRadius() {
		return radius;
	}

	public double getOldRadius() {
		return oldRadius;
	}

	public double getNewRadius() {
		return newRadius;
	}

	public long getSpeed() {
		return speed;
	}

	public double getX() {
		return x;
	}

	public double getZ() {
		return z;
	}

	public int getPortalTeleportBoundary() {
		return portalTeleportBoundary;
	}

	public int getWarningTime() {
		return warningTime;
	}

	public int getWarningBlocks() {
		return warningBlocks;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x44;
	}
}