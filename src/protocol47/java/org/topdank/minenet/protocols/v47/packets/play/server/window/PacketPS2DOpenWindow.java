package org.topdank.minenet.protocols.v47.packets.play.server.window;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS2DOpenWindow implements IdentifiableReadablePacket {

	private int windowId;
	private String windowType;
	private String windowTitle;
	private int slotCount;
	private int horseEntityId;

	@Override
	public void read(ReadableInput in) throws IOException {
		windowId = in.readUnsignedByte();
		windowType = in.readString();
		windowTitle = in.readString();
		slotCount = in.readUnsignedByte();
		if (isHorseInventory())
			horseEntityId = in.readInt();
	}

	public int getWindowId() {
		return windowId;
	}

	public String getWindowType() {
		return windowType;
	}

	public String getWindowTitle() {
		return windowTitle;
	}

	public int getSlotCount() {
		return slotCount;
	}

	public boolean isHorseInventory() {
		return (windowType != null) && windowType.equals("EntityHorse");
	}

	public int getHorseEntityId() {
		return horseEntityId;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x2D;
	}
}