package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS23BlockChange implements IdentifiableReadablePacket {

	private BlockLocation loc;
	private int data;

	public PacketPS23BlockChange() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		loc = Protocol47DataHelper.decodePosition(in);
		data = in.readVarInt();
	}

	public BlockLocation getLocation() {
		return loc;
	}

	public int getData() {
		return data;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x23;
	}
}