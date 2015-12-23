package org.topdank.minenet.protocols.v47.packets.play.server.entity;

import java.io.IOException;
import java.util.Map;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS1CEntityMetadata implements IdentifiableReadablePacket {

	private int entityId;
	private Map<Integer, Object> metadata;

	public PacketPS1CEntityMetadata() {
	}

	// Type Meaning
	// 0 Byte
	// 1 Short
	// 2 Int
	// 3 Float
	// 4 UTF-8 String (VarInt prefixed)
	// 5 Slot
	// 6* Int, Int, Int (x, y, z)
	// 7 Float, Float, Float (pitch, yaw, roll)

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		metadata = Protocol47DataHelper.readEntityMetadata(in);
	}

	public int getEntityId() {
		return entityId;
	}

	public Map<Integer, Object> getMetadata() {
		return metadata;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x1C;
	}

	public static final class Position {
		private final int x, y, z;

		public Position(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
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
	}
}