package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS33UpdateSign implements IdentifiableReadablePacket {

	private BlockLocation position;
	private String[] lines;

	@Override
	public void read(ReadableInput in) throws IOException {
		position = Protocol47DataHelper.decodePosition(in);
		lines = new String[4];
		for (int i = 0; i < lines.length; i++) {
			lines[i] = in.readString();
		}
	}

	public BlockLocation getPosition() {
		return position;
	}

	public String[] getLines() {
		return lines;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x33;
	}
}