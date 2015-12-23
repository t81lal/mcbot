package org.topdank.minenet.protocols.v47.packets.play.client.window;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;

public class PacketPC0DCloseWindow implements IdentifiableWriteablePacket {

	private byte windowId;

	public PacketPC0DCloseWindow(byte windowId) {
		this.windowId = windowId;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeByte(windowId);
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x0D;
	}
}