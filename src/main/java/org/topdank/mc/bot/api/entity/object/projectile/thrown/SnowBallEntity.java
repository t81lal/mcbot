package org.topdank.mc.bot.api.entity.object.projectile.thrown;

import org.topdank.mc.bot.api.entity.object.ThrownEntity;
import org.topdank.mc.bot.api.world.World;

public class SnowBallEntity extends ThrownEntity {

	public SnowBallEntity(World world, int id) {
		super(world, id, 0.25F, 0.25F);
	}
}