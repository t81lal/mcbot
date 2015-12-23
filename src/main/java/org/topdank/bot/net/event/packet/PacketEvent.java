package org.topdank.bot.net.event.packet;

import org.topdank.bot.net.Client;
import org.topdank.bot.net.event.CancellableClientEvent;
import org.topdank.bot.net.packet.Packet;

public class PacketEvent<T extends Packet> extends CancellableClientEvent {

	private final T packet;

	public PacketEvent(Client<?> client, T packet) {
		super(client);
		this.packet = packet;
	}

	public T getPacket() {
		return packet;
	}
}