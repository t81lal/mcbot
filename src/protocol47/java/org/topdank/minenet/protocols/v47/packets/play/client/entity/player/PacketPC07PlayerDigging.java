package org.topdank.minenet.protocols.v47.packets.play.client.entity.player;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPC07PlayerDigging implements IdentifiableWriteablePacket {

	private byte status;
	private BlockLocation loc;
	private byte face;

	public PacketPC07PlayerDigging(byte status, BlockLocation loc, byte face) {
		this.status = status;
		this.loc = loc;
		this.face = face;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeByte(status);
		out.writeLong(Protocol47DataHelper.encodePosition(loc));
		out.writeByte(face);
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x07;
	}
}