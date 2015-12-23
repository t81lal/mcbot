package org.topdank.mc.bot.api.world.chunk;

public class NetworkChunkData {
	
	private int mask;
	private boolean fullChunk;
	private boolean sky;
	private byte data[];
	
	public NetworkChunkData(int mask, boolean fullChunk, boolean sky, byte data[]) {
		this.mask = mask;
		this.fullChunk = fullChunk;
		this.sky = sky;
		this.data = data;
	}
	
	public int getMask() {
		return mask;
	}
	
	public boolean isFullChunk() {
		return fullChunk;
	}
	
	public boolean hasSkyLight() {
		return sky;
	}
	
	public byte[] getData() {
		return data;
	}
}