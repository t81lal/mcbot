package org.topdank.mc.bot.api.event.internal.world;

import org.topdank.bot.eventbus.Event;
import org.topdank.mc.bot.api.world.chunk.Chunk;

public class InternalMultiChunkLoadEvent implements Event {

	private final int[] xs, ys, zs;
	private final Chunk[] chunks;
	private byte[] biomess;

	public InternalMultiChunkLoadEvent(int[] x, int[] y, int[] z, Chunk[] chunk, byte[] biomes) {
		xs = x;
		ys = y;
		zs = z;
		chunks = chunk;
		biomess = biomes;
	}

	public int[] getChunkXs() {
		return xs;
	}

	public int[] getChunkYs() {
		return ys;
	}

	public int[] getChunkZs() {
		return zs;
	}

	public Chunk[] getChunks() {
		return chunks;
	}

	public byte[] getBiomess() {
		return biomess;
	}
}