package org.topdank.minenet.protocols.v47.packets.play.client.entity.player;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;
import org.topdank.mc.bot.api.item.ItemStack;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPC08BlockPlace implements IdentifiableWriteablePacket {

	private BlockLocation loc;
	private byte face;
	private ItemStack heldItem;
	private float cursorX;
	private float cursorY;
	private float cursorZ;

	public PacketPC08BlockPlace(BlockLocation loc, byte face, ItemStack heldItem, float cursorX, float cursorY, float cursorZ) {
		this.loc = loc;
		this.face = face;
		this.heldItem = heldItem;
		this.cursorX = cursorX;
		this.cursorY = cursorY;
		this.cursorZ = cursorZ;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeLong(Protocol47DataHelper.encodePosition(loc));
		out.writeByte(face);
		Protocol47DataHelper.writeItemStack(out, heldItem);
		out.writeByte((int) (cursorX * 16));
		out.writeByte((int) (cursorY * 16));
		out.writeByte((int) (cursorZ * 16));
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x08;
	}
}