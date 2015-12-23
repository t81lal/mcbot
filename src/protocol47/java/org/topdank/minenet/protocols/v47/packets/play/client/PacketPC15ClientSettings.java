package org.topdank.minenet.protocols.v47.packets.play.client;

import java.io.IOException;

import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableWriteablePacket;

public class PacketPC15ClientSettings implements IdentifiableWriteablePacket {

	private String locale;
	private byte renderChunks;
	private byte chatVisibility;
	private boolean chatColours;
	private byte displayedSkinPartFlags;

	public PacketPC15ClientSettings(String locale, byte renderChunks, byte chatVisibility, boolean chatColours, byte displayedSkinPartFlags) {
		this.locale = locale;
		this.renderChunks = renderChunks;
		this.chatVisibility = chatVisibility;
		this.chatColours = chatColours;
		this.displayedSkinPartFlags = displayedSkinPartFlags;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeString(locale);
		out.writeByte(renderChunks);
		out.writeByte(chatVisibility);
		out.writeBoolean(chatColours);
		out.writeByte(displayedSkinPartFlags);
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x15;
	}
}