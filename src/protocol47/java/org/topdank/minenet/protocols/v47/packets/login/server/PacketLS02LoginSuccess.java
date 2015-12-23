package org.topdank.minenet.protocols.v47.packets.login.server;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketLS02LoginSuccess implements IdentifiableReadablePacket {

	private String uuid;
	private String name;

	public PacketLS02LoginSuccess() {

	}

	@Override
	public void read(ReadableInput in) throws IOException {
		uuid = in.readString();
		name = in.readString();
	}

	public String getUUID() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean isPriorityPacket() {
		return true;
	}

	@Override
	public int getId() {
		return 0x02;
	}
}