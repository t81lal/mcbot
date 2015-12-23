package org.topdank.mc.bot.api.world.chunk;

public class ParsedChunkData {
	
	private Chunk chunks[];
	private byte biomes[];
	
	public ParsedChunkData(Chunk chunks[], byte biomes[]) {
		this.chunks = chunks;
		this.biomes = biomes;
	}
	
	public Chunk[] getChunks() {
		return chunks;
	}
	
	public byte[] getBiomes() {
		return biomes;
	}
}