package org.topdank.bot.net.event.disconnect;

import org.topdank.bot.net.Client;
import org.topdank.bot.net.event.ClientEvent;

public class DisconnectingEvent extends ClientEvent {
	
	private final String reason;

	public DisconnectingEvent(Client<?> client, String reason) {
		super(client);
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}
}