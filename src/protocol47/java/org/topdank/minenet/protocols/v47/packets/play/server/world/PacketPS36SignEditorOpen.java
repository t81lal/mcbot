package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS36SignEditorOpen implements IdentifiableReadablePacket {

	private BlockLocation loc;

	@Override
	public void read(ReadableInput in) throws IOException {
		loc = Protocol47DataHelper.decodePosition(in);
	}

	public BlockLocation getLocation() {
		return loc;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x36;
	}
}