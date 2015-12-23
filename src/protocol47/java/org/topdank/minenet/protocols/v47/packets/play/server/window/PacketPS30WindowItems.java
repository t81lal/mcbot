package org.topdank.minenet.protocols.v47.packets.play.server.window;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.item.ItemStack;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS30WindowItems implements IdentifiableReadablePacket {

	private int windowId;
	private ItemStack[] slots;

	@Override
	public void read(ReadableInput in) throws IOException {
		windowId = in.readByte();
		short count = in.readShort();
		slots = new ItemStack[count];
		for (int i = 0; i < count; i++) {
			slots[i] = Protocol47DataHelper.readItemStack(in);
		}
	}

	public int getWindowId() {
		return windowId;
	}

	public ItemStack[] getSlots() {
		return slots;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x30;
	}
}