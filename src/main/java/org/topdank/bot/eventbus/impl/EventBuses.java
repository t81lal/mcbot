package org.topdank.bot.eventbus.impl;

import org.topdank.bot.eventbus.EventBus;

public final class EventBuses {

	public static final EventBus singleThreadBus() {
		return new DefaultEventBus();
	}

	public static final EventBus multiThreadBus(int threads) {
		throw new UnsupportedOperationException("Multithreaded EventBuses aren't supported yet.");
	}

	public static final EventBus threadSafeBus() {
		return new ConcurrentEventBus();
	}
}