package org.topdank.mc.bot.api.world.chunk;

import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.mc.bot.api.world.pos.ChunkLocation;

public class Chunk {

	private ChunkLocation chunkLocation;
	private BlockLocation blockLocation;
	private ShortArray3d blocks;
	private NibbleArray3d blocklight;
	private NibbleArray3d skylight;

	public Chunk(boolean skylight, ChunkLocation loc) {
		this(loc, new ShortArray3d(4096), new NibbleArray3d(4096), skylight ? new NibbleArray3d(4096) : null);
	}

	public Chunk(ChunkLocation chunkLocation, ShortArray3d blocks, NibbleArray3d blocklight, NibbleArray3d skylight) {
		this.chunkLocation = chunkLocation;
		blockLocation = new BlockLocation(chunkLocation);
		this.blocks = blocks;
		this.blocklight = blocklight;
		this.skylight = skylight;
	}

	public ChunkLocation getChunkLocation() {
		return chunkLocation;
	}

	public BlockLocation getBlockLocation() {
		return blockLocation;
	}

	public ShortArray3d getBlocks() {
		return blocks;
	}

	public NibbleArray3d getBlockLight() {
		return blocklight;
	}

	public NibbleArray3d getSkyLight() {
		return skylight;
	}

	public boolean isEmpty() {
		for (short block : blocks.getData()) {
			if (block != 0) {
				return false;
			}
		}
		return true;
	}
}