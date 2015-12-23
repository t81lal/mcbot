package org.topdank.mc.bot.impl.event;

import org.topdank.bot.eventbus.EventCancellable;

public class PluginMessageEvent extends EventCancellable {
	
	private String channel;
	private String msg;
	private Direction direction;
	
	public PluginMessageEvent(String channel, String msg, Direction direction) {
		this.channel = channel;
		this.msg = msg;
		this.direction = direction;
	}
	
	public String getChannel() {
		return channel;
	}
	
	public String getMessage() {
		return msg;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public enum Direction {
		SERVER(),
		CLIENT();
	}
}