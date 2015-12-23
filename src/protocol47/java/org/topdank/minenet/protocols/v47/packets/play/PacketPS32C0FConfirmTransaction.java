package org.topdank.minenet.protocols.v47.packets.play;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableReadableWriteablePacket;

public class PacketPS32C0FConfirmTransaction implements IdentifiableReadableWriteablePacket {

	private final int id;
	private byte windowId;
	private short actionId;
	private boolean accepted;

	public PacketPS32C0FConfirmTransaction() {
		id = 0x32;
	}

	public PacketPS32C0FConfirmTransaction(byte windowId, short actionId, boolean accepted) {
		id = 0x0F;
		this.windowId = windowId;
		this.actionId = actionId;
		this.accepted = accepted;
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		windowId = in.readByte();
		actionId = in.readShort();
		accepted = in.readBoolean();
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeByte(windowId);
		out.writeShort(actionId);
		out.writeBoolean(accepted);
	}

	public byte getWindowId() {
		return windowId;
	}

	public short getActionId() {
		return actionId;
	}

	public boolean isAccepted() {
		return accepted;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return id;
	}
}