package org.topdank.mc.bot.api.world.pos;

public final class ChunkLocation {

	private final int x;
	private final int y;
	private final int z;

	public ChunkLocation(int x, int y, int z, boolean chunk) {
		if (chunk) {
			this.x = x;
			this.y = y;
			this.z = z;
		} else {
			this.x = x >> 4;
			this.y = y >> 4;
			this.z = z >> 4;
		}
	}

	public ChunkLocation(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public ChunkLocation(BlockLocation loc) {
		this(loc.getX() >> 4, loc.getY() >> 4, loc.getZ() >> 4);
	}

	public ChunkLocation(PreciseLocation loc) {
		this((int) (loc.getX()) >> 4, (int) (loc.getY()) >> 4, (int) (loc.getZ()) >> 4);
	}

	public BlockLocation toBlockLocation() {
		return new BlockLocation(this);
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

	@Override
	public String toString() {
		return "ChunkLocation [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + x;
		result = (prime * result) + y;
		result = (prime * result) + z;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChunkLocation other = (ChunkLocation) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}
}