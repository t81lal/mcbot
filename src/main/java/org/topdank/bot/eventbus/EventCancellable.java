package org.topdank.bot.eventbus;

public abstract class EventCancellable implements Event {
	
	protected boolean cancelled;
	
	public EventCancellable() {
		super();
	}
	
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}
}