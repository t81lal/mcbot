package org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal;

import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.AgeableEntity;

public class ChickenEntity extends AgeableEntity {

	public ChickenEntity(World world, int id) {
		super(world, id, 0.4F, 0.7F);
	}
}