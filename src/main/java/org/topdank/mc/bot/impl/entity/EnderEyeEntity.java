package org.topdank.mc.bot.impl.entity;

import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.world.World;

public class EnderEyeEntity extends Entity {

	public EnderEyeEntity(World world, int id) {
		super(world, id, 0.25F, 0.25F);
	}
}