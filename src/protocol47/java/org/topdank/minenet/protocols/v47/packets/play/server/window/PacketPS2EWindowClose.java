package org.topdank.minenet.protocols.v47.packets.play.server.window;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS2EWindowClose implements IdentifiableReadablePacket {

	private int windowId;

	public PacketPS2EWindowClose() {
	}

	public int getWindowId() {
		return windowId;
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		windowId = in.readUnsignedByte();
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x2E;
	}
}