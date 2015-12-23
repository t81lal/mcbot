package org.topdank.bot.net.event;

public interface EventListener<T extends ClientEvent> {

	void onEvent(T e);
	
	Class<? extends T> getEventClass();
}