package org.topdank.mc.bot.api.entity.object.item;

import org.topdank.mc.bot.api.entity.object.ObjectEntity;
import org.topdank.mc.bot.api.world.World;

public class LeashKnotEntity extends ObjectEntity {

	public LeashKnotEntity(World world, int id) {
		super(world, id, 0.5F, 0.5F);
	}
}