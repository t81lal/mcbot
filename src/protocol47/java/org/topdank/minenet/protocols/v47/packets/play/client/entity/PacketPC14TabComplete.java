package org.topdank.minenet.protocols.v47.packets.play.client.entity;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPC14TabComplete implements IdentifiableWriteablePacket {

	private String text;
	private BlockLocation loc;

	public PacketPC14TabComplete(String text) {
		this.text = text;
		loc = null;
	}

	public PacketPC14TabComplete(String text, BlockLocation loc) {
		this.text = text;
		this.loc = loc;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeString(text);
		if (loc != null)
			out.writeLong(Protocol47DataHelper.encodePosition(loc));
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x14;
	}
}