package org.topdank.mc.bot.impl.entity.living.creature;

import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.impl.entity.living.LivingEntity;

public abstract class CreatureEntity extends LivingEntity {

	public CreatureEntity(World world, int id, float width, float height) {
		super(world, id, width, height);
	}
}