package org.topdank.mc.bot.api.world;

import org.topdank.mc.bot.api.world.pos.BoundingBox;

public class Block {

	private static final BoundingBox DEFAULT = BoundingBox.create(0, 0, 0, 1, 1, 1);
	private static final BoundingBox NONE = BoundingBox.create(0, 0, 0, 0, 0, 0);

	private final World world;
	private final int x, y, z;
	private final BlockId id;
	private final BlockData data;
	private final BoundingBox[] boxes;
	private final BoundingBox concatBox;

	public Block(World world, int x, int y, int z, BlockId id, BlockData data) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = id;
		this.data = data;
		BoundingBox box = DEFAULT.offset(x, y, z);
		boxes = new BoundingBox[] { box };
		concatBox = box;
	}

	public Block(World world, int x, int y, int z, BlockId id, BlockData data, BoundingBox... bbs) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = id;
		this.data = data;
		boxes = bbs;
		concatBox = concatBox(bbs);
	}

	protected BoundingBox concatBox(BoundingBox[] bbs) {
		if (bbs.length == 1)
			return bbs[0];
		double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE, minZ = Double.MAX_VALUE;
		double maxX = -Double.MAX_VALUE, maxY = -Double.MAX_VALUE, maxZ = -Double.MAX_VALUE;
		for (BoundingBox bb : bbs) {
			minX = Math.min(minX, bb.getMinX());
			minY = Math.min(minY, bb.getMinY());
			minZ = Math.min(minZ, bb.getMinZ());
			maxX = Math.max(maxX, bb.getMaxX());
			maxY = Math.max(maxY, bb.getMaxY());
			maxZ = Math.max(maxZ, bb.getMaxZ());
		}
		if ((minX > maxX) || (minY > maxY) || (minZ > maxZ))
			return NONE;
		return BoundingBox.create(minX, minY, minZ, maxX, maxY, maxZ);
	}

	public World getWorld() {
		return world;
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

	public BlockId getId() {
		return id;
	}

	public BlockData getBlockData() {
		return data;
	}

	public BoundingBox[] getBoundingBoxes() {
		return boxes;
	}

	public BoundingBox getConcatBox() {
		return concatBox;
	}
}