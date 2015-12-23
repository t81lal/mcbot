package org.topdank.bot.net.event;

import org.topdank.bot.eventbus.EventCancellable;
import org.topdank.bot.net.Client;

public class CancellableClientEvent extends EventCancellable {

	private final Client<?> client;
	
	public CancellableClientEvent(Client<?> client) {
		this.client = client;
	}
	
	public Client<?> getClient() {
		return client;
	}
}