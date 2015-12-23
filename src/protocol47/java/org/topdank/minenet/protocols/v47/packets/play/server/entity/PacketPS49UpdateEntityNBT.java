package org.topdank.minenet.protocols.v47.packets.play.server.entity;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.nbt.tag.builtin.CompoundTag;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS49UpdateEntityNBT implements IdentifiableReadablePacket {

	private int entityId;
	private CompoundTag tag;

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		tag = Protocol47DataHelper.readNBT(in);
	}

	public int getEntityId() {
		return entityId;
	}

	public CompoundTag getTag() {
		return tag;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x49;
	}
}