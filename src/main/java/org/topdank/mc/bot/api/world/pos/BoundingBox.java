package org.topdank.mc.bot.api.world.pos;

/**
 * @author Darkstorm
 */
public final class BoundingBox {

	public static final BoundingBox NORMAL_BOUNDING_BOX = create(0, 0, 0, 1, 1, 1);

	private final double minX, minY, minZ;
	private final double maxX, maxY, maxZ;

	public static BoundingBox create(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		return new BoundingBox(Math.min(minX, maxX), Math.min(minY, maxY), Math.min(minZ, maxZ), Math.max(minX, maxX), Math.max(minY, maxY), Math.max(minZ, maxZ));
	}

	public static BoundingBox create(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
		return create((double) minX, (double) minY, (double) minZ, (double) maxX, (double) maxY, (double) maxZ);
	}

	protected BoundingBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;
		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
	}

	public BoundingBox expandBy(double x, double y, double z) {
		if (((x + 0.0) == 0) && ((y + 0.0) == 0) && ((z + 0.0) == 0))
			return this;
		double newMinX = minX, newMinY = minY, newMinZ = minZ;
		double newMaxX = maxX, newMaxY = maxY, newMaxZ = maxZ;
		if (x < 0)
			newMinX += x;
		else
			newMaxX += x;
		if (y < 0)
			newMinY += y;
		else
			newMaxY += y;
		if (z < 0)
			newMinZ += z;
		else
			newMaxZ += z;
		return create(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
	}

	public BoundingBox expand(double offX, double offY, double offZ) {
		if (((offX + 0.0) == 0) && ((offY + 0.0) == 0) && ((offZ + 0.0) == 0))
			return this;
		double newMinX = minX - offX, newMinY = minY - offY, newMinZ = minZ - offZ;
		double newMaxX = maxX + offX, newMaxY = maxY + offY, newMaxZ = maxZ + offZ;
		if (newMinX > newMaxX)
			newMinX = newMaxX = (double) ((newMinX + newMaxX) / 2.0);
		if (newMinY > newMaxY)
			newMinY = newMaxY = (double) ((newMinY + newMaxY) / 2.0);
		if (newMinZ > newMaxZ)
			newMinZ = newMaxZ = (double) ((newMinZ + newMaxZ) / 2.0);
		return create(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
	}

	public BoundingBox contract(double offX, double offY, double offZ) {
		return expand(-offX, -offY, -offZ);
	}

	public BoundingBox offset(double offX, double offY, double offZ) {
		if (((offX + 0.0) == 0) && ((offY + 0.0) == 0) && ((offZ + 0.0) == 0))
			return this;
		return create(minX + offX, minY + offY, minZ + offZ, maxX + offX, maxY + offY, maxZ + offZ);
	}

	public BoundingBox offset(PreciseLocation location) {
		return offset((double) location.getX(), (double) location.getY(), (double) location.getZ());
	}

	public BoundingBox offset(BlockLocation location) {
		return offset(location.getX(), location.getY(), location.getZ());
	}

	public BoundingBox include(double x, double y, double z) {
		return create(Math.min(minX, x), Math.min(minY, y), Math.min(minZ, z), Math.max(maxX, x), Math.max(maxY, y), Math.max(maxZ, z));
	}

	public double boundedShiftX(BoundingBox other, double offX) {
		if ((minY >= other.getMaxY()) || (maxY <= other.getMinY()) || (minZ >= other.getMaxZ()) || (maxZ <= other.getMinZ()))
			return offX;
		if ((offX > 0) && (minX >= other.getMaxX())) {
			double diffX = minX - other.getMaxX();
			if (diffX < offX)
				return diffX;
		} else if ((offX < 0) && (maxX <= other.getMinX())) {
			double diffX = maxX - other.getMinX();
			if (diffX > offX)
				return diffX;
		}
		return offX;
	}

	public double boundedShiftY(BoundingBox other, double offY) {
		if ((minX >= other.getMaxX()) || (maxX <= other.getMinX()) || (minZ >= other.getMaxZ()) || (maxZ <= other.getMinZ()))
			return offY;
		if ((offY > 0) && (minY >= other.getMaxY())) {
			double diffY = minY - other.getMaxY();
			if (diffY < offY)
				return diffY;
		} else if ((offY < 0) && (maxY <= other.getMinY())) {
			double diffY = maxY - other.getMinY();
			if (diffY > offY)
				return diffY;
		}
		return offY;
	}

	public double boundedShiftZ(BoundingBox other, double offZ) {
		if ((minX >= other.getMaxX()) || (maxX <= other.getMinX()) || (minY >= other.getMaxY()) || (maxY <= other.getMinY()))
			return offZ;
		if ((offZ > 0) && (minZ >= other.getMaxZ())) {
			double diffZ = minZ - other.getMaxZ();
			if (diffZ < offZ)
				return diffZ;
		} else if ((offZ < 0) && (maxZ <= other.getMinZ())) {
			double diffZ = maxZ - other.getMinZ();
			if (diffZ > offZ)
				return diffZ;
		}
		return offZ;
	}

	public boolean intersectsWith(BoundingBox other) {
		if ((minX >= other.getMaxX()) || (maxX <= other.getMinX()))
			return false;
		if ((minY >= other.getMaxY()) || (maxY <= other.getMinY()))
			return false;
		if ((minZ >= other.getMaxZ()) || (maxZ <= other.getMinZ()))
			return false;
		return true;
	}

	public boolean contains(BlockLocation location) {
		return contains(location.getX(), location.getY(), location.getZ());
	}

	public boolean contains(double x, double y, double z) {
		if ((minX >= x) || (maxX <= x))
			return false;
		if ((minY >= y) || (maxY <= y))
			return false;
		if ((minZ >= z) || (maxZ <= z))
			return false;
		return true;
	}

	public double getMinX() {
		return minX;
	}

	public double getMinY() {
		return minY;
	}

	public double getMinZ() {
		return minZ;
	}

	public double getMaxX() {
		return maxX;
	}

	public double getMaxY() {
		return maxY;
	}

	public double getMaxZ() {
		return maxZ;
	}

	public BlockLocation getMin() {
		return new BlockLocation(minX, minY, minZ);
	}

	public BlockLocation getMax() {
		return new BlockLocation(maxX, maxY, maxZ);
	}

	public double getWidth() {
		return maxX - minX;
	}

	public double getLength() {
		return maxZ - minZ;
	}

	public double getHeight() {
		return maxY - minY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(maxX);
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(maxY);
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(maxZ);
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(minX);
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(minY);
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(minZ);
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
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
		BoundingBox other = (BoundingBox) obj;
		if (Double.doubleToLongBits(maxX) != Double.doubleToLongBits(other.maxX))
			return false;
		if (Double.doubleToLongBits(maxY) != Double.doubleToLongBits(other.maxY))
			return false;
		if (Double.doubleToLongBits(maxZ) != Double.doubleToLongBits(other.maxZ))
			return false;
		if (Double.doubleToLongBits(minX) != Double.doubleToLongBits(other.minX))
			return false;
		if (Double.doubleToLongBits(minY) != Double.doubleToLongBits(other.minY))
			return false;
		if (Double.doubleToLongBits(minZ) != Double.doubleToLongBits(other.minZ))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("BoundingBox[[%.4f, %.4f, %.4f], [%.4f, %.4f, %.4f]]", minX, minY, minZ, maxX, maxY, maxZ);
	}
}