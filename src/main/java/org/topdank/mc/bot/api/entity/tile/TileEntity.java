package org.topdank.mc.bot.api.entity.tile;

import org.topdank.mc.bot.api.nbt.tag.builtin.CompoundTag;
import org.topdank.mc.bot.api.world.World;

public abstract class TileEntity {

	private final World world;
	private double x, y, z;
	private CompoundTag nbt;

	public TileEntity(World world) {
		this(world, null);
	}

	public TileEntity(World world, CompoundTag nbt) {
		this.world = world;
		this.nbt = nbt;
	}

	public World getWorld() {
		return world;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public CompoundTag getNbt() {
		return nbt;
	}

	public void setNbt(CompoundTag nbt) {
		this.nbt = nbt;
	}
}