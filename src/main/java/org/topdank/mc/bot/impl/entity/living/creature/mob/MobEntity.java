package org.topdank.mc.bot.impl.entity.living.creature.mob;

import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.impl.entity.living.creature.CreatureEntity;

public abstract class MobEntity extends CreatureEntity {

	public MobEntity(World world, int id, float width, float height) {
		super(world, id, width, height);
	}
}