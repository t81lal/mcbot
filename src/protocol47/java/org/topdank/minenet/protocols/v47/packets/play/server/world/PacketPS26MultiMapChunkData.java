package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.world.chunk.Chunk;
import org.topdank.mc.bot.api.world.chunk.NetworkChunkData;
import org.topdank.mc.bot.api.world.chunk.ParsedChunkData;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS26MultiMapChunkData implements IdentifiableReadablePacket {

	private int[] x;
	private int[] z;
	private Chunk[][] chunks;
	private byte[][] biomeData;

	public PacketPS26MultiMapChunkData() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		boolean skylight = in.readBoolean();
		int columns = in.readVarInt();
		x = new int[columns];
		z = new int[columns];
		chunks = new Chunk[columns][];
		biomeData = new byte[columns][];
		NetworkChunkData[] data = new NetworkChunkData[columns];
		for (int column = 0; column < columns; column++) {
			x[column] = in.readInt();
			z[column] = in.readInt();
			int mask = in.readUnsignedShort();
			int chunks = Integer.bitCount(mask);
			int length = (chunks * ((4096 * 2) + 2048)) + (skylight ? chunks * 2048 : 0) + 256;
			byte dat[] = new byte[length];
			data[column] = new NetworkChunkData(mask, true, skylight, dat);
		}
		for (int column = 0; column < columns; column++) {
			in.readBytes(data[column].getData());
			ParsedChunkData chunkData = Protocol47DataHelper.dataToChunks(x[column], z[column], data[column], false);
			chunks[column] = chunkData.getChunks();
			biomeData[column] = chunkData.getBiomes();
		}
	}

	public int getColumns() {
		return chunks.length;
	}

	public int getX(int column) {
		return x[column];
	}

	public int getZ(int column) {
		return z[column];
	}

	public Chunk[] getChunks(int column) {
		return chunks[column];
	}

	public byte[] getBiomeData(int column) {
		return biomeData[column];
	}

	public int[] getX() {
		return x;
	}

	public int[] getZ() {
		return z;
	}

	public Chunk[][] getChunks() {
		return chunks;
	}

	public byte[][] getBiomeData() {
		return biomeData;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x26;
	}
}