package org.topdank.mc.bot.api;

public abstract class Controller<T> {
	
	private final BotContext context;
	private T obj;
	
	public Controller(BotContext context) {
		this.context = context;
	}
	
	public BotContext getContext() {
		return context;
	}

	public T get() {
		return obj;
	}

	public void set(T t) {
		obj = t;
	}
}