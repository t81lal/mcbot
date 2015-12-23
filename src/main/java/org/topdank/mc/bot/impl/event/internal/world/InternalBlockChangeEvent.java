package org.topdank.mc.bot.impl.event.internal.world;

import org.topdank.bot.eventbus.Event;
import org.topdank.mc.bot.api.world.pos.BlockLocation;

public class InternalBlockChangeEvent implements Event {

	private final int data, x, y, z;

	public InternalBlockChangeEvent(int data, BlockLocation loc) {
		this.data = data;
		x = loc.getX();
		y = loc.getY();
		z = loc.getZ();
	}

	public InternalBlockChangeEvent(int data, int x, int y, int z) {
		this.data = data;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getData() {
		return data;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
}