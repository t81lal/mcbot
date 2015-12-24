package org.topdank.mc.bot.impl.event.spawn;

import org.topdank.bot.eventbus.Event;
import org.topdank.mc.bot.api.entity.Entity;

public class LivingEntitySpawnEvent implements Event {

	private Entity livingEntity;

	public LivingEntitySpawnEvent(Entity livingEntity) {
		this.livingEntity = livingEntity;
	}

	public Entity getObjectEntity() {
		return livingEntity;
	}
}