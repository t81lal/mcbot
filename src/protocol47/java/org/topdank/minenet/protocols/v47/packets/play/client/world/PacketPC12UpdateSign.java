package org.topdank.minenet.protocols.v47.packets.play.client.world;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPC12UpdateSign implements IdentifiableWriteablePacket {

	private BlockLocation loc;
	private String[] text;

	public PacketPC12UpdateSign(BlockLocation loc, String[] text) {
		this.loc = loc;
		this.text = text;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeLong(Protocol47DataHelper.encodePosition(loc));
		for (String s : text) {
			out.writeString(s);
		}
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x12;
	}
}