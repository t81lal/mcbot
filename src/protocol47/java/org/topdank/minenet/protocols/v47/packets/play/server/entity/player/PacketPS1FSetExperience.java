package org.topdank.minenet.protocols.v47.packets.play.server.entity.player;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS1FSetExperience implements IdentifiableReadablePacket {

	private float expBar;
	private int level;
	private int totalxp;

	public PacketPS1FSetExperience() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		expBar = in.readFloat();
		level = in.readVarInt();
		totalxp = in.readVarInt();
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	public float getExpBar() {
		return expBar;
	}

	public int getLevel() {
		return level;
	}

	public int getTotalXP() {
		return totalxp;
	}

	@Override
	public int getId() {
		return 0x1F;
	}
}