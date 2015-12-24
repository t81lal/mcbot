package org.topdank.mc.bot.api.event;

import org.topdank.bot.eventbus.Event;

public final class TickEvent implements Event {
	
	public static final Event INSTANCE = new TickEvent();
	
	private TickEvent() {
	}
}