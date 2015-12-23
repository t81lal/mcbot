package org.topdank.mc.bot.api.entity.object.projectile;

import org.topdank.mc.bot.api.entity.object.ProjectileEntity;
import org.topdank.mc.bot.api.world.World;

public class FireBallEntity extends ProjectileEntity {

	public FireBallEntity(World world, int id) {
		super(world, id, 1F, 1F);
	}

	public FireBallEntity(World world, int id, float width, float height) {
		super(world, id, width, height);
	}
}