package org.topdank.bot.net.event.packet;

import org.topdank.bot.net.Client;
import org.topdank.bot.net.packet.ReadablePacket;

public class PacketReceivedEvent extends PacketEvent<ReadablePacket> {

	public PacketReceivedEvent(Client<?> client, ReadablePacket packet) {
		super(client, packet);
	}
}