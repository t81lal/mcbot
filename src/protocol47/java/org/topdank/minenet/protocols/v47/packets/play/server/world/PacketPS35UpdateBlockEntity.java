package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.nbt.tag.builtin.CompoundTag;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS35UpdateBlockEntity implements IdentifiableReadablePacket {

	private BlockLocation position;
	private int type;
	private CompoundTag nbt;

	@Override
	public void read(ReadableInput in) throws IOException {
		position = Protocol47DataHelper.decodePosition(in);
		type = in.readUnsignedByte();
		nbt = Protocol47DataHelper.readNBT(in);
	}

	public BlockLocation getPosition() {
		return position;
	}

	public int getType() {
		return type;
	}

	public CompoundTag getNBT() {
		return nbt;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x35;
	}
}