package org.topdank.minenet.protocols.v47.packets.play.server.general;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS2BGameStateChange implements IdentifiableReadablePacket {

	private int reason;
	private float value;

	public PacketPS2BGameStateChange() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		reason = in.readUnsignedByte();
		value = in.readFloat();
	}

	public int getReason() {
		return reason;
	}

	public float getValue() {
		return value;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x2B;
	}
}