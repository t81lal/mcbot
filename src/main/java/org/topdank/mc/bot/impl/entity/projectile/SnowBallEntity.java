package org.topdank.mc.bot.impl.entity.projectile;

import org.topdank.mc.bot.api.world.World;

public class SnowBallEntity extends ProjectileEntity {

	public SnowBallEntity(World world, int id) {
		super(world, id, 0.25F, 0.25F);
	}
}