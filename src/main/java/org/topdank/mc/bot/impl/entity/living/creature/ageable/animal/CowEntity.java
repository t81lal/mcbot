package org.topdank.mc.bot.impl.entity.living.creature.ageable.animal;

import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.impl.entity.living.creature.ageable.AgeableEntity;

public class CowEntity extends AgeableEntity {

	public CowEntity(World world, int id) {
		super(world, id, 0.9F, 1.3F);
	}
}