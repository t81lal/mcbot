package org.topdank.minenet.protocols.v47.packets.play.server.entity.player;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableReadableWriteablePacket;

public class PacketPSC09HeldItemChange implements IdentifiableReadableWriteablePacket {

	private int slot;

	public PacketPSC09HeldItemChange() {
	}

	public PacketPSC09HeldItemChange(int slot) {
		this.slot = slot;

	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeByte(slot);
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		slot = in.readByte();
	}

	public int getSlot() {
		return slot;
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