package org.topdank.mc.bot.impl.event.spawn;

import org.topdank.bot.eventbus.Event;
import org.topdank.mc.bot.api.entity.living.LivingEntity;

public class LivingEntitySpawnEvent implements Event {

	private LivingEntity livingEntity;

	public LivingEntitySpawnEvent(LivingEntity livingEntity) {
		this.livingEntity = livingEntity;
	}

	public LivingEntity getObjectEntity() {
		return livingEntity;
	}
}