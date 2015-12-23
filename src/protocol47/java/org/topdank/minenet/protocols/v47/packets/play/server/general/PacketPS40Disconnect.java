package org.topdank.minenet.protocols.v47.packets.play.server.general;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS40Disconnect implements IdentifiableReadablePacket {

	private String reason;

	public PacketPS40Disconnect() {
	}

	@Override
	public void read(ReadableInput input) throws IOException {
		reason = input.readString();
	}

	public String getReason() {
		return reason;
	}

	@Override
	public boolean isPriorityPacket() {
		return true;
	}

	@Override
	public int getId() {
		return 0x40;
	}
}