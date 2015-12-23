package org.topdank.minenet.protocols.v47.packets.play.client.entity.player;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;

public class PacketPC0ASwingArm implements IdentifiableWriteablePacket {

	public static final PacketPC0ASwingArm INSTANCE = new PacketPC0ASwingArm();

	@Override
	public void write(WriteableOutput out) throws IOException {
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x0A;
	}
}