package org.topdank.minenet.protocols.v47.packets.play.client.entity.player;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;

public class PacketPC13PlayerAbilities implements IdentifiableWriteablePacket {

	private byte flags;
	private float flyingSpeed;
	private float walkingSpeed;

	public PacketPC13PlayerAbilities(byte flags, float flyingSpeed, float walkingSpeed) {
		this.flags = flags;
		this.flyingSpeed = flyingSpeed;
		this.walkingSpeed = walkingSpeed;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeByte(flags);
		out.writeFloat(flyingSpeed);
		out.writeFloat(walkingSpeed);
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x13;
	}
}