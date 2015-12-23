package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.world.pos.BlockLocation;

public class PacketPS27Explosion implements IdentifiableReadablePacket {

	private float x, y, z;
	private float radius;
	private BlockLocation[] relativeDestroyedBlocks;
	private float playerMotionX, playerMotionY, playerMotionZ;

	@Override
	public void read(ReadableInput in) throws IOException {
		x = in.readFloat();
		y = in.readFloat();
		z = in.readFloat();
		radius = in.readFloat();
		int length = in.readInt();
		relativeDestroyedBlocks = new BlockLocation[length];
		for (int i = 0; i < length; i++) {
			relativeDestroyedBlocks[i] = new BlockLocation(in.readByte(), in.readByte(), in.readByte());
		}
		x = in.readFloat();
		y = in.readFloat();
		z = in.readFloat();
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public float getRadius() {
		return radius;
	}

	public BlockLocation[] getRelativeDestroyedBlocks() {
		return relativeDestroyedBlocks;
	}

	public float getPlayerMotionX() {
		return playerMotionX;
	}

	public float getPlayerMotionY() {
		return playerMotionY;
	}

	public float getPlayerMotionZ() {
		return playerMotionZ;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x27;
	}
}