package org.topdank.mc.bot.impl.event.spawn;

import org.topdank.bot.eventbus.Event;
import org.topdank.mc.bot.api.entity.object.ObjectEntity;

public class ObjectEntitySpawnEvent implements Event {

	private ObjectEntity objectEntity;

	public ObjectEntitySpawnEvent(ObjectEntity objectEntity) {
		this.objectEntity = objectEntity;
	}

	public ObjectEntity getObjectEntity() {
		return objectEntity;
	}
}