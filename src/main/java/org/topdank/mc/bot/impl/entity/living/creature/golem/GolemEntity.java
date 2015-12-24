package org.topdank.mc.bot.impl.entity.living.creature.golem;

import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.impl.entity.living.creature.CreatureEntity;

public abstract class GolemEntity extends CreatureEntity {

	public GolemEntity(World world, int id, float width, float height) {
		super(world, id, width, height);
	}
}