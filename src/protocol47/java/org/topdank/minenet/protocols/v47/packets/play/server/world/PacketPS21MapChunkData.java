package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.world.chunk.Chunk;
import org.topdank.mc.bot.api.world.chunk.NetworkChunkData;
import org.topdank.mc.bot.api.world.chunk.ParsedChunkData;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS21MapChunkData implements IdentifiableReadablePacket {

	private int x;
	private int z;
	private Chunk[] chunks;
	private byte[] biomeData;

	public PacketPS21MapChunkData() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		x = in.readInt();
		z = in.readInt();
		boolean fullChunk = in.readBoolean();
		int chunkMask = in.readUnsignedShort();
		byte data[] = in.readBytes(in.readVarInt());
		ParsedChunkData chunkData = Protocol47DataHelper.dataToChunks(x, z, new NetworkChunkData(chunkMask, fullChunk, false, data), true);
		chunks = chunkData.getChunks();
		biomeData = chunkData.getBiomes();
	}

	public int getX() {
		return x;
	}

	public int getZ() {
		return z;
	}

	public Chunk[] getChunks() {
		return chunks;
	}

	public byte[] getBiomeData() {
		return biomeData;
	}

	public boolean isFullChunk() {
		return biomeData != null;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x21;
	}
}