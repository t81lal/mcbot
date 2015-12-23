package org.topdank.minenet.protocols.v47.packets.play.server.general;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS43Camera implements IdentifiableReadablePacket {

	private int cameraId;

	@Override
	public void read(ReadableInput input) throws IOException {
		cameraId = input.readVarInt();
	}

	public int getCameraId() {
		return cameraId;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x43;
	}
}