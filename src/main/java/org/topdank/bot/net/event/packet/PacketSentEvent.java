package org.topdank.bot.net.event.packet;

import org.topdank.bot.net.Client;
import org.topdank.bot.net.packet.WriteablePacket;

public class PacketSentEvent extends PacketEvent<WriteablePacket> {

	public PacketSentEvent(Client<?> client, WriteablePacket packet) {
		super(client, packet);
	}
}