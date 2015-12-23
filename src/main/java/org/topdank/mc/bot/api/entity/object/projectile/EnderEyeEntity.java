package org.topdank.mc.bot.api.entity.object.projectile;

import org.topdank.mc.bot.api.entity.object.ProjectileEntity;
import org.topdank.mc.bot.api.world.World;

public class EnderEyeEntity extends ProjectileEntity {

	public EnderEyeEntity(World world, int id) {
		super(world, id, 0.25F, 0.25F);
	}
}