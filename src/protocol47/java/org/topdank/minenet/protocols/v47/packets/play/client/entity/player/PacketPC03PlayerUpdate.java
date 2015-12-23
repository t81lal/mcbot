package org.topdank.minenet.protocols.v47.packets.play.client.entity.player;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;

public class PacketPC03PlayerUpdate implements IdentifiableWriteablePacket {

	protected double x;
	protected double y;
	protected double z;

	protected float yaw;
	protected float pitch;

	protected boolean onGround;

	protected boolean pos = false;
	protected boolean rot = false;

	public PacketPC03PlayerUpdate(boolean onGround) {
		this.onGround = onGround;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		if (pos) {
			out.writeDouble(x);
			out.writeDouble(y);
			out.writeDouble(z);
			// System.out.println("send: " + x + " " + y + " " + z);
		}
		if (rot) {
			out.writeFloat(yaw);
			out.writeFloat(pitch);
		}
		out.writeBoolean(onGround);
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x03;
	}
}