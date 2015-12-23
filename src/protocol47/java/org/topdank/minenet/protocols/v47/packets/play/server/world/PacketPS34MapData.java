package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS34MapData implements IdentifiableReadablePacket {

	private int mapId;
	private byte scale;
	private MapPlayer players[];
	private MapData data;

	public PacketPS34MapData() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		mapId = in.readVarInt();
		scale = in.readByte();
		players = new MapPlayer[in.readVarInt()];
		for (int index = 0; index < players.length; index++) {
			int data = in.readUnsignedByte();
			int size = (data >> 4) & 15;
			int rotation = data & 15;
			int x = in.readUnsignedByte();
			int z = in.readUnsignedByte();
			players[index] = new MapPlayer(x, z, size, rotation);
		}
		int columns = in.readUnsignedByte();
		if (columns > 0) {
			int rows = in.readUnsignedByte();
			int x = in.readUnsignedByte();
			int y = in.readUnsignedByte();
			byte data[] = in.readBytes(in.readVarInt());
			this.data = new MapData(columns, rows, x, y, data);
		}
	}

	public int getMapId() {
		return mapId;
	}

	public byte getScale() {
		return scale;
	}

	public MapPlayer[] getPlayers() {
		return players;
	}

	public MapData getData() {
		return data;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x34;
	}

	public class MapData {

		private int columns;
		private int rows;
		private int x;
		private int y;
		private byte data[];

		public MapData(int columns, int rows, int x, int y, byte data[]) {
			this.columns = columns;
			this.rows = rows;
			this.x = x;
			this.y = y;
			this.data = data;
		}

		public int getColumns() {
			return columns;
		}

		public int getRows() {
			return rows;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public byte[] getData() {
			return data;
		}
	}

	public class MapPlayer {

		private int centerX;
		private int centerZ;
		private int iconSize;
		private int iconRotation;

		public MapPlayer(int centerX, int centerZ, int iconSize, int iconRotation) {
			this.centerX = centerX;
			this.centerZ = centerZ;
			this.iconSize = iconSize;
			this.iconRotation = iconRotation;
		}

		public int getCenterX() {
			return centerX;
		}

		public int getCenterZ() {
			return centerZ;
		}

		public int getIconSize() {
			return iconSize;
		}

		public int getIconRotation() {
			return iconRotation;
		}
	}
}