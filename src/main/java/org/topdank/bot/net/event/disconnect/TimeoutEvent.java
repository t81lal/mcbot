package org.topdank.bot.net.event.disconnect;

import org.topdank.bot.net.Client;

public class TimeoutEvent {

	private final Client<?> client;
	private final TimeoutType type;

	public TimeoutEvent(Client<?> client, TimeoutType type) {
		this.client = client;
		this.type = type;
	}

	public Client<?> getClient() {
		return client;
	}

	public TimeoutType getType() {
		return type;
	}
}