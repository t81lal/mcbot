package org.topdank.mc.bot.impl.entity.fireball;

import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.world.World;

public abstract class FireBallEntity extends Entity {

	public FireBallEntity(World world, int id) {
		super(world, id, 1F, 1F);
	}

	public FireBallEntity(World world, int id, float width, float height) {
		super(world, id, width, height);
	}
}