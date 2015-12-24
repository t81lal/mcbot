package org.topdank.mc.bot.impl.entity.impl.projectile;

import org.topdank.mc.bot.api.world.World;

public class EggEntity extends ProjectileEntity {

	public EggEntity(World world, int id) {
		super(world, id, 0.25F, 0.25F);
	}
}