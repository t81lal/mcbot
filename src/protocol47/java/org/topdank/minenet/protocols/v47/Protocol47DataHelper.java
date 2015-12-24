package org.topdank.minenet.protocols.v47;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.HashMap;
import java.util.Map;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.io.stream.NetInputStream;
import org.topdank.bot.net.io.stream.NetOutputStream;
import org.topdank.mc.bot.api.item.BasicItemStack;
import org.topdank.mc.bot.api.item.ItemStack;
import org.topdank.mc.bot.api.nbt.NBTIO;
import org.topdank.mc.bot.api.nbt.tag.builtin.CompoundTag;
import org.topdank.mc.bot.api.world.chunk.Chunk;
import org.topdank.mc.bot.api.world.chunk.NetworkChunkData;
import org.topdank.mc.bot.api.world.chunk.NibbleArray3d;
import org.topdank.mc.bot.api.world.chunk.ParsedChunkData;
import org.topdank.mc.bot.api.world.chunk.ShortArray3d;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.mc.bot.api.world.pos.ChunkLocation;
import org.topdank.mc.bot.api.world.pos.Rotation;
import org.topdank.mc.util.MathHelper;

public class Protocol47DataHelper {

	public static BlockLocation decodePosition(ReadableInput in) throws IOException {
		long serialised = in.readLong();
		int x = (int) ((serialised << (64 - MathHelper.BLOCK_XY_ZY_POS_MAX_LONG_DECODE_BITS - MathHelper.BLOCK_X_POS_MAX_LONG_DECODE_BITS)) >> (64 - MathHelper.BLOCK_X_POS_MAX_LONG_DECODE_BITS));
		int y = (int) ((serialised << (64 - MathHelper.BLOCK_Y_POS_MAX_LONG__DECODE_BITS_COPY - MathHelper.BLOCK_Y_POS_MAX_LONG__DECODE_BITS)) >> (64 - MathHelper.BLOCK_Y_POS_MAX_LONG__DECODE_BITS));
		int z = (int) ((serialised << (64 - MathHelper.BLOCK_Z_POS_MAX_LONG_DECODE_BITS)) >> (64 - MathHelper.BLOCK_Z_POS_MAX_LONG_DECODE_BITS));
		return new BlockLocation(x, y, z);
	}

	@Deprecated
	public static BlockLocation decodePositionOld(ReadableInput in) throws IOException {
		long val = in.readLong();
		int x = (int) (val >> 38);
		int y = (int) ((val >> 26) & 0xFFF);
		int z = (int) ((val << 38) >> 38);
		return new BlockLocation(x, y, z);
	}

	public static long encodePosition(BlockLocation loc) throws IOException {
		return ((loc.getX() & MathHelper.BLOCK_X_POS_MAX_LONG_ENCODE_BITS) << MathHelper.BLOCK_XY_ZY_POS_MAX_LONG_DECODE_BITS)
				| ((loc.getY() & MathHelper.BLOCK_Y_POS_MAX_LONG_ENCODE_BITS) << MathHelper.BLOCK_Y_POS_MAX_LONG__DECODE_BITS_COPY)
				| ((loc.getZ() & MathHelper.BLOCK_Z_POS_MAX_LONG_ENCODE_BITS) << 0);
	}

	/**
	 * Doesnt work
	 *
	 * @param loc
	 * @return
	 * @throws IOException
	 */
	@Deprecated
	public static long encodePositionOld(BlockLocation loc) throws IOException {
		return ((loc.getX() & 0x3FFFFFF) << 38) | ((loc.getY() & 0xFFF) << 26) | (loc.getZ() & 0x3FFFFFF);
	}

	public static ItemStack readItemStack(ReadableInput in) throws IOException {
		short item = in.readShort();
		if (item < 0) {
			return null;
		} else {
			return new BasicItemStack(item, in.readByte(), in.readShort(), readNBT(in));
		}
	}

	public static void writeItemStack(WriteableOutput out, ItemStack item) throws IOException {
		if (item == null) {
			out.writeShort(-1);
		} else {
			out.writeShort(item.getId());
			out.writeByte(item.getStackSize());
			out.writeShort(item.getDamage());
			writeNBT(out, item.getStackTagCompound());
		}
	}

	public static CompoundTag readNBT(ReadableInput in) throws IOException {
		byte b = in.readByte();
		if (b == 0) {
			return null;
		} else {
			return (CompoundTag) NBTIO.readTag(new DataInputStream((new NetInputStream(in, b))));
		}
	}

	public static void writeNBT(WriteableOutput out, CompoundTag tag) throws IOException {
		if (tag == null) {
			out.writeByte(0);
		} else {
			NBTIO.writeTag(new DataOutputStream(new NetOutputStream(out)), tag);
		}
	}

	public static ParsedChunkData dataToChunks(int x, int z, NetworkChunkData data, boolean checkForSky) {
		Chunk chunks[] = new Chunk[16];
		int pos = 0;
		int expected = 0;
		boolean sky = false;
		ShortBuffer buf = ByteBuffer.wrap(data.getData()).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer();
		// 0 = Calculate expected length and determine if the packet has
		// skylight.
		// 1 = Create chunks from mask and get blocks.
		// 2 = Get block light.
		// 3 = Get sky light.
		for (int pass = 0; pass < 4; pass++) {
			for (int ind = 0; ind < 16; ind++) {
				if ((data.getMask() & (1 << ind)) != 0) {
					if (pass == 0) {
						// Block length + Blocklight length
						expected += (4096 * 2) + 2048;
					}
					if (pass == 1) {
						chunks[ind] = new Chunk(sky || data.hasSkyLight(), new ChunkLocation(x, ind, z));
						ShortArray3d blocks = chunks[ind].getBlocks();
						buf.position(pos / 2);
						buf.get(blocks.getData(), 0, blocks.getData().length);
						pos += blocks.getData().length * 2;
					}
					if (pass == 2) {
						NibbleArray3d blocklight = chunks[ind].getBlockLight();
						System.arraycopy(data.getData(), pos, blocklight.getData(), 0, blocklight.getData().length);
						pos += blocklight.getData().length;
					}
					if ((pass == 3) && (sky || data.hasSkyLight())) {
						NibbleArray3d skylight = chunks[ind].getSkyLight();
						System.arraycopy(data.getData(), pos, skylight.getData(), 0, skylight.getData().length);
						pos += skylight.getData().length;
					}
				}
			}
			if (pass == 0) {
				// If we have more data than blocks and blocklight combined,
				// there must be skylight data as well.
				if (data.getData().length >= expected) {
					sky = checkForSky;
				}
			}
		}
		byte biomeData[] = null;
		if (data.isFullChunk()) {
			biomeData = new byte[256];
			System.arraycopy(data.getData(), pos, biomeData, 0, biomeData.length);
			pos += biomeData.length;
		}
		return new ParsedChunkData(chunks, biomeData);
	}

	public static Map<Integer, Object> readEntityMetadata(ReadableInput in) throws IOException {
		Map<Integer, Object> metadata = new HashMap<Integer, Object>();
		byte b;
		while ((b = in.readByte()) != 127) {
			int typeId = (b & 0xE0) >> 5;
			int id = b & 0x1F;
			Object value = null;
			switch (typeId) {
				case 0:
					value = in.readByte();
					break;
				case 1:
					value = in.readShort();
					break;
				case 2:
					value = in.readInt();
					break;
				case 3:
					value = in.readFloat();
					break;
				case 4:
					value = in.readString();
					break;
				case 5:
					value = readItemStack(in);
					break;
				case 6:
					value = new BlockLocation(in.readInt(), in.readInt(), in.readInt());
					break;
				case 7:
					value = new Rotation(in.readFloat(), in.readFloat(), in.readFloat());
					// value = new float[] { in.readFloat(), in.readFloat(), in.readFloat() };
					break;
				default:
					throw new IOException("Unknown metadata type id: " + typeId);
			}
			metadata.put(id, value);
		}
		return metadata;
	}
}