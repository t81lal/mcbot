package org.topdank.minenet.protocols.v47.packets.play.server.entity.player;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS08PositionRotation implements IdentifiableReadablePacket {

	private static final int[] BITS = new int[] { 0x01, 0x02, 0x04, 0x08, 0x10 };

	private double x;
	private double y;
	private double z;
	private float yaw;
	private float pitch;
	private boolean[] relatives;

	public PacketPS08PositionRotation() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		x = in.readDouble();
		y = in.readDouble();
		z = in.readDouble();
		yaw = in.readFloat();
		pitch = in.readFloat();

		int flags = in.readUnsignedByte();
		relatives = new boolean[5];

		for (int i = 0; i < BITS.length; i++) {
			int bit = 1 << BITS[i];
			relatives[i] = (flags & bit) == bit;
		}
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

	public boolean[] getRelativeStates() {
		return relatives;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x08;
	}
}