package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.world.pos.BlockLocation;

public class PacketPS22MultiBlockChange implements IdentifiableReadablePacket {

	private int length;
	private BlockLocation[] loc;
	private int[] datas;

	public PacketPS22MultiBlockChange() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		int chunkX = in.readInt();
		int chunkZ = in.readInt();
		length = in.readVarInt();
		loc = new BlockLocation[length];
		datas = new int[length];

		for (int index = 0; index < length; index++) {
			short pos = in.readShort();
			int data = in.readVarInt();
			int x = (chunkX << 4) + ((pos >> 12) & 15);
			int y = pos & 255;
			int z = (chunkZ << 4) + ((pos >> 8) & 15);
			loc[index] = new BlockLocation(x, y, z);
			datas[index] = data;
		}
	}

	public int getLength() {
		return length;
	}

	public BlockLocation getLocation(int i) {
		return loc[i];
	}

	public int getBlockData(int i) {
		return datas[i];
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x22;
	}
}