package org.topdank.minenet.protocols.v47.packets.login.server;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketLS00Disconnect implements IdentifiableReadablePacket {

	private String data;

	public PacketLS00Disconnect() {

	}

	@Override
	public void read(ReadableInput input) throws IOException {
		data = input.readString();
	}

	public String getData() {
		return data;
	}

	@Override
	public boolean isPriorityPacket() {
		return true;
	}

	@Override
	public int getId() {
		return 0x00;
	}
}