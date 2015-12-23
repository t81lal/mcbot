package org.topdank.bot.eventbus;

public interface EventBus {

	void register(Object src);

	void register(Object src, Class<? extends Event> eventClass);

	void register(Object src, Class<? extends Event>[] eventClass);

	void unregister(Object src);

	void unregister(Object src, Class<? extends Event> eventClass);

	void unregister(Object src, Class<? extends Event>[] eventClass);

	void dispatch(Event event);

	void dispatch(Event[] events);
}