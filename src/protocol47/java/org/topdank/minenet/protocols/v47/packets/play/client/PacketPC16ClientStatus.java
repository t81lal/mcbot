package org.topdank.minenet.protocols.v47.packets.play.client;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;

public class PacketPC16ClientStatus implements IdentifiableWriteablePacket {

	private ClientStatusAction action;

	public PacketPC16ClientStatus(ClientStatusAction action) {
		this.action = action;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeVarInt(action.ordinal());
	}

	@Override
	public boolean isPriorityPacket() {
		return true;
	}

	@Override
	public int getId() {
		return 0x16;
	}

	public enum ClientStatusAction {
		RESPAWN,
		STATS,
		ACHEIVEMENT;
	}
}