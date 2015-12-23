package org.topdank.minenet.protocols.v47.packets.play.client.entity.player;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;

public class PacketPC0CSteerVehicle implements IdentifiableWriteablePacket {

	private float sideways;
	private float forward;
	private byte flags;

	public PacketPC0CSteerVehicle(float sideways, float forward, byte flags) {
		this.sideways = sideways;
		this.forward = forward;
		this.flags = flags;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeFloat(sideways);
		out.writeFloat(forward);
		out.writeByte(flags);
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x0C;
	}
}