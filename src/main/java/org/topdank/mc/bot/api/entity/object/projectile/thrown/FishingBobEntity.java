package org.topdank.mc.bot.api.entity.object.projectile.thrown;

import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.entity.object.ThrownEntity;
import org.topdank.mc.bot.api.world.World;

public class FishingBobEntity extends ThrownEntity {

	public FishingBobEntity(World world, int id, Entity thrower) {
		super(world, id, 0.25F, 0.25F, thrower);
	}
	
	public FishingBobEntity(World world, int id) {
		this(world, id, null);
	}
}