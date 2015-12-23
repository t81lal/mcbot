package org.topdank.minenet.protocols.v47.packets.play.server.window;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.item.ItemStack;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS2FSetSlot implements IdentifiableReadablePacket {

	private int windowId;
	private int slot;
	private ItemStack item;

	public PacketPS2FSetSlot() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		windowId = in.readUnsignedByte();
		slot = in.readShort();
		item = Protocol47DataHelper.readItemStack(in);
	}

	public int getWindowId() {
		return windowId;
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
		return 0x2F;
	}
}