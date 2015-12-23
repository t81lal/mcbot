package org.topdank.minenet.protocols.v47.packets.play.server.entity.player;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS06UpdateHealth implements IdentifiableReadablePacket {

	private float health;
	private int food;
	private float foodSaturation;

	public PacketPS06UpdateHealth() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		health = in.readFloat();
		food = in.readVarInt();
		foodSaturation = in.readFloat();
	}

	public float getHealth() {
		return health;
	}

	public int getFood() {
		return food;
	}

	public float getFoodSaturation() {
		return foodSaturation;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x06;
	}
}