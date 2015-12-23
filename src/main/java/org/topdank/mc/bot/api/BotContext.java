package org.topdank.mc.bot.api;

import org.topdank.bot.eventbus.EventBus;
import org.topdank.mc.bot.MCClient;
import org.topdank.mc.bot.api.entity.EntityFactoryProvider;
import org.topdank.mc.bot.api.world.World;

public class BotContext {
	
	private MCClient client;
	private EntityFactoryProvider entityProvider;
	private World world;
	
	public BotContext(MCClient client, EntityFactoryProvider entityProvider) {
		this.client = client;
		this.entityProvider = entityProvider;
	}
	
	public EntityFactoryProvider getEntityProvider() {
		return entityProvider;
	}
	
	public void setEntityProvider(EntityFactoryProvider entityProvider) {
		this.entityProvider = entityProvider;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public EventBus getEventBus() {
		return client.getEventBus();
	}
	
	public World getWorld() {
		return world;
	}
}
