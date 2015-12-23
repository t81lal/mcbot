package org.topdank.minenet.protocols.v47.packets.play.server.general;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS48ResourcePackSend implements IdentifiableReadablePacket {

	private String url;
	private String hash;

	@Override
	public void read(ReadableInput in) throws IOException {
		url = in.readString();
		hash = in.readString();
	}

	public String getUrl() {
		return url;
	}

	public String getHash() {
		return hash;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x48;
	}
}