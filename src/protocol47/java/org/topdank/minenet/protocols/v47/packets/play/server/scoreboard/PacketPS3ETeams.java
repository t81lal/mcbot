package org.topdank.minenet.protocols.v47.packets.play.server.scoreboard;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS3ETeams implements IdentifiableReadablePacket {

	// (TeamAction.CREATE, 0);
	// (TeamAction.REMOVE, 1);
	// (TeamAction.UPDATE, 2);
	// (TeamAction.ADD_PLAYER, 3);
	// (TeamAction.REMOVE_PLAYER, 4);

	@Override
	public void read(ReadableInput in) throws IOException {
		in.readString();// name
		byte action = in.readByte();// action
		if ((action == 0) || (action == 2)) {
			in.readString();// displayName
			in.readString();// prefix
			in.readString();// suffix
			in.readByte();// friendlyFire
			in.readString();// nameTagVisibility
			in.readByte();// colour
		}
		if ((action == 0) || (action == 3) || (action == 4)) {
			int len = in.readVarInt();
			for (int i = 0; i < len; i++) {
				in.readString();// member name
			}
		}
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x3E;
	}
}