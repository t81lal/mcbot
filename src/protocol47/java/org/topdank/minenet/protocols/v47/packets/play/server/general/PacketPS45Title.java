package org.topdank.minenet.protocols.v47.packets.play.server.general;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS45Title implements IdentifiableReadablePacket {

	public PacketPS45Title() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		TitleAction action = TitleAction.values()[in.readVarInt()];
		switch (action) {
			case TITLE:
				in.readString();
				break;
			case SUBTITLE:
				in.readString();
				break;
			case TIMES:
				in.readInt();
				in.readInt();
				in.readInt();
				break;
			case CLEAR:
				break;
			case RESET:
				break;
		}
	}

	public enum TitleAction {
		TITLE,
		SUBTITLE,
		TIMES,
		CLEAR,
		RESET;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x45;
	}
}