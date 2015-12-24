package org.topdank.mc.bot.impl.event.spawn;

import org.topdank.bot.eventbus.Event;
import org.topdank.mc.bot.impl.entity.Entity;

public class ObjectEntitySpawnEvent implements Event {

	private Entity objectEntity;

	public ObjectEntitySpawnEvent(Entity objectEntity) {
		this.objectEntity = objectEntity;
	}

	public Entity getObjectEntity() {
		return objectEntity;
	}
}