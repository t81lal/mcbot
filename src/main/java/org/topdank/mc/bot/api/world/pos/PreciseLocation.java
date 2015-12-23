package org.topdank.mc.bot.api.world.pos;

public final class PreciseLocation {

	protected final double x;
	protected final double y;
	protected final double z;

	public PreciseLocation(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public PreciseLocation(PreciseLocation loc) {
		x = loc.x;
		y = loc.y;
		z = loc.z;
	}

	public PreciseLocation(BlockLocation loc) {
		x = loc.x;
		y = loc.y;
		z = loc.z;
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

	public PreciseLocation offset(PreciseLocation loc) {
		return new PreciseLocation(x + loc.x, y + loc.y, z + loc.z);
	}

	public PreciseLocation offset(BlockLocation loc) {
		// faster
		return new PreciseLocation(x + loc.x, y + loc.y, z + loc.z);
	}

	public PreciseLocation offset(double x, double y, double z) {
		// faster
		return new PreciseLocation(this.x + x, this.y + y, this.z + z);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PreciseLocation))
			return false;
		PreciseLocation location = (PreciseLocation) obj;
		return (location.getX() == x) && (location.getY() == y) && (location.getZ() == z);
	}

	@Override
	public String toString() {
		return "PreciseLocation [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
}