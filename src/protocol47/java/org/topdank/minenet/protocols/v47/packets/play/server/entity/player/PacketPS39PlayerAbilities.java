package org.topdank.minenet.protocols.v47.packets.play.server.entity.player;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS39PlayerAbilities implements IdentifiableReadablePacket {

	private boolean isGod;
	private boolean canFly;
	private boolean isFlying;
	private boolean isInreative;
	private float flySpeed;
	private float walkSpeed;

	@Override
	public void read(ReadableInput in) throws IOException {
		byte flags = in.readByte();
		isGod = (flags & 1) == 1;
		canFly = (flags & 2) == 1;
		isFlying = (flags & 4) == 1;
		isInreative = (flags & 8) == 0;
		flySpeed = in.readFloat();
		walkSpeed = in.readFloat();
	}

	public boolean isGod() {
		return isGod;
	}

	public boolean isCanFly() {
		return canFly;
	}

	public boolean isFlying() {
		return isFlying;
	}

	public boolean isInreative() {
		return isInreative;
	}

	public float getFlySpeed() {
		return flySpeed;
	}

	public float getWalkSpeed() {
		return walkSpeed;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x39;
	}
}