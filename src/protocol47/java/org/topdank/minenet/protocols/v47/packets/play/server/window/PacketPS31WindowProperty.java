package org.topdank.minenet.protocols.v47.packets.play.server.window;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS31WindowProperty implements IdentifiableReadablePacket {

	private int windowId;
	private short propertyId;
	private short propertyValue;

	@Override
	public void read(ReadableInput in) throws IOException {
		windowId = in.readUnsignedByte();
		propertyId = in.readShort();
		propertyValue = in.readShort();
	}

	public int getWindowId() {
		return windowId;
	}

	public short getPropertyId() {
		return propertyId;
	}

	public short getPropertyValue() {
		return propertyValue;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x31;
	}
}