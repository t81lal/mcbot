package org.topdank.bot.net.event.disconnect;

import org.topdank.bot.net.Client;
import org.topdank.bot.net.event.ClientEvent;

public class DisconnectedEvent extends ClientEvent {

	private final String reason;

	public DisconnectedEvent(Client<?> client, String reason) {
		super(client);
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}
}