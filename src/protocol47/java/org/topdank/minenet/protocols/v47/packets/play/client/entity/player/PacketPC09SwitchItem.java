package org.topdank.minenet.protocols.v47.packets.play.client.entity.player;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;

public class PacketPC09SwitchItem implements IdentifiableWriteablePacket {

	private byte slot;

	public PacketPC09SwitchItem(byte slot) {
		this.slot = slot;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeShort(slot);
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x09;
	}
}