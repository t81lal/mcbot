package org.topdank.mc.bot;

import org.topdank.bot.net.Client;
import org.topdank.bot.net.event.packet.PacketEvent;
import org.topdank.bot.net.packet.WriteablePacket;

public class RequestPacketSendEvent extends PacketEvent<WriteablePacket> {

	public RequestPacketSendEvent(Client<?> client, WriteablePacket packet) {
		super(client, packet);
	}
}