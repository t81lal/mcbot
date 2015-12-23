package org.topdank.minenet.protocols.v47.packets.play.client;

import java.io.IOException;
import java.util.UUID;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;

public class PacketPC18Spectate implements IdentifiableWriteablePacket {

	private UUID targetUUID;

	public PacketPC18Spectate(UUID targetUUID) {
		this.targetUUID = targetUUID;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeUUID(targetUUID);
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x17;
	}
}