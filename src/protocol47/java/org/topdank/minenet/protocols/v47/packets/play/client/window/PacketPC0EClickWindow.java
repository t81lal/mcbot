package org.topdank.minenet.protocols.v47.packets.play.client.window;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;
import org.topdank.mc.bot.api.item.ItemStack;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPC0EClickWindow implements IdentifiableWriteablePacket {

	private byte windowId;
	private short slot;
	private byte button;
	private short actionId;
	private byte operationMode;
	private ItemStack clicked;

	public PacketPC0EClickWindow(byte windowId, short slot, byte button, short actionId, byte operationMode, ItemStack clicked) {
		this.windowId = windowId;
		this.slot = slot;
		this.button = button;
		this.actionId = actionId;
		this.operationMode = operationMode;
		this.clicked = clicked;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeByte(windowId);
		out.writeShort(slot);
		out.writeByte(button);
		out.writeShort(actionId);
		out.writeByte(operationMode);
		Protocol47DataHelper.writeItemStack(out, clicked);
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x0E;
	}
}