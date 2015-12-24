package org.topdank.mc.bot.impl.entity.fireball;

import org.topdank.mc.bot.api.world.World;

public class SmallFireBallEntity extends FireBallEntity {

	public SmallFireBallEntity(World world, int id) {
		super(world, id, 0.3125F, 0.3125F);
	}
}