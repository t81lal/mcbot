package org.topdank.minenet.protocols.v47.packets.play.client;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;

public class PacketPC11EnchantItem implements IdentifiableWriteablePacket {

	private byte windowId;
	private byte enchantment;

	public PacketPC11EnchantItem(byte windowId, byte enchantment) {
		this.windowId = windowId;
		this.enchantment = enchantment;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeByte(windowId);
		out.writeByte(enchantment);
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x11;
	}
}