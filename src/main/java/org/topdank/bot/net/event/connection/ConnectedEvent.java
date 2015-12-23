package org.topdank.bot.net.event.connection;

import org.topdank.bot.net.Client;
import org.topdank.bot.net.event.ClientEvent;

public class ConnectedEvent extends ClientEvent {

	public ConnectedEvent(Client<?> client) {
		super(client);
	}
}