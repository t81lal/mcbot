package org.topdank.minenet.protocols.v47.packets.play.client.window;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;
import org.topdank.mc.bot.api.item.ItemStack;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPC10CreativeInventoryAction implements IdentifiableWriteablePacket {

	private short slot;
	private ItemStack clicked;

	public PacketPC10CreativeInventoryAction(short slot, ItemStack clicked) {
		this.slot = slot;
		this.clicked = clicked;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeShort(slot);
		Protocol47DataHelper.writeItemStack(out, clicked);
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x10;
	}
}