package org.topdank.mc.bot.api.world.chunk;

public final class ChunkData {
	
	private final int x, z;
	private final int primaryBitmask;
	private final byte[] data;
	
	protected ChunkData(int x, int z, int primaryBitmask, byte[] data) {
		this.x = x;
		this.z = z;
		this.primaryBitmask = primaryBitmask;
		this.data = data;
	}
	
	public int getX() {
		return x;
	}
	
	public int getZ() {
		return z;
	}
	
	public int getMask() {
		return primaryBitmask;
	}
	
	public byte[] getData() {
		return data;
	}
}