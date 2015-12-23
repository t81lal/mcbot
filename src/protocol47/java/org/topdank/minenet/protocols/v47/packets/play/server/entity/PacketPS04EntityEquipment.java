package org.topdank.minenet.protocols.v47.packets.play.server.entity;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.item.ItemStack;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS04EntityEquipment implements IdentifiableReadablePacket {

	private int entityId;
	private int slot;
	private ItemStack item;

	public PacketPS04EntityEquipment() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readVarInt();
		slot = in.readShort();
		item = Protocol47DataHelper.readItemStack(in);
	}

	public int getEntityId() {
		return entityId;
	}

	public int getSlot() {
		return slot;
	}

	public ItemStack getItem() {
		return item;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x04;
	}
}