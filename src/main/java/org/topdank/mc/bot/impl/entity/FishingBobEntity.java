package org.topdank.mc.bot.impl.entity;

import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.world.World;

public class FishingBobEntity extends Entity {

	private Entity fisher;
	
	public FishingBobEntity(World world, int id, Entity thrower) {
		super(world, id, 0.25F, 0.25F);
		this.fisher = thrower;
	}
	
	public FishingBobEntity(World world, int id) {
		this(world, id, null);
	}

	public Entity getFisher() {
		return fisher;
	}

	public void setFisher(Entity fisher) {
		this.fisher = fisher;
	}
	
}