package org.topdank.mc.bot.api.world.pos;

public final class BlockLocation {
	
	protected final int x;
	protected final int y;
	protected final int z;
	
	public BlockLocation(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public BlockLocation(double x, double y, double z) {
		this.x = (int) x;
		this.y = (int) y;
		this.z = (int) z;
	}
	
	public BlockLocation(BlockLocation loc) {
		x = loc.x;
		y = loc.y;
		z = loc.z;
	}
	
	public BlockLocation(PreciseLocation loc) {
		x = (int) loc.x;
		y = (int) loc.y;
		z = (int) loc.z;
	}
	
	public BlockLocation(ChunkLocation loc) {
		x = loc.getX() << 4;
		y = loc.getY() << 4;
		z = loc.getZ() << 4;
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
	
	public double distance(double x, double y, double z) {
		return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) + Math.pow(this.z - z, 2));
	}
	
	public double distance(PreciseLocation loc) {
		// faster
		return Math.sqrt(Math.pow(x - loc.x, 2) + Math.pow(y - loc.y, 2) + Math.pow(z - loc.z, 2));
	}
	
	public double distance(BlockLocation loc) {
		// faster
		return Math.sqrt(Math.pow(x - loc.x, 2) + Math.pow(y - loc.y, 2) + Math.pow(z - loc.z, 2));
	}
	
	public double distanceSquared(double x, double y, double z) {
		return Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) + Math.pow(this.z - z, 2);
	}
	
	public double distanceSquared(PreciseLocation other) {
		// faster
		return Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2);
	}
	
	public double distanceSquared(BlockLocation other) {
		// faster
		return Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2);
	}
	
	public BlockLocation offset(PreciseLocation loc) {
		return new BlockLocation(x + (int) loc.x, y + (int) loc.y, z + (int) loc.z);
	}
	
	public BlockLocation offset(BlockLocation loc) {
		// faster
		return new BlockLocation(x + loc.x, y + loc.y, z + loc.z);
	}
	
	public BlockLocation offset(double x, double y, double z) {
		// faster
		return new BlockLocation(this.x + (int) x, this.y + (int) y, this.z + (int) z);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BlockLocation))
			return false;
		BlockLocation location = (BlockLocation) obj;
		return (location.getX() == x) && (location.getY() == y) && (location.getZ() == z);
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
	public String toString() {
		return "BlockLocation [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
}