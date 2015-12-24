package org.topdank.mc.bot.impl.entity.impl.living.creature.mob;

import org.topdank.mc.bot.api.world.World;

public class GiantZombieEntity extends MobEntity {

	public GiantZombieEntity(World world, int id) {
		super(world, id, 0.6F * 6F, 1.95F * 6F);
	}
}