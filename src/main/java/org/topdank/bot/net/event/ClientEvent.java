package org.topdank.bot.net.event;

import org.topdank.bot.eventbus.Event;
import org.topdank.bot.net.Client;

public class ClientEvent implements Event {
	
	private final Client<?> client;

	public ClientEvent(Client<?> client) {
		this.client = client;
	}

	public Client<?> getClient() {
		return client;
	}
}