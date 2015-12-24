package org.topdank.mc.bot.api.event.world;

import org.topdank.bot.eventbus.Event;

public class TimeUpdateEvent implements Event {

	private long time, age;

	public TimeUpdateEvent(long time, long age) {
		this.time = time;
		this.age = age;
	}

	public long getTime() {
		return time;
	}

	public long getAge() {
		return age;
	}
}