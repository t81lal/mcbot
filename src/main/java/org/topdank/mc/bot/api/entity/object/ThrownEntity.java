package org.topdank.mc.bot.api.entity.object;

import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.world.World;

public class ThrownEntity extends ProjectileEntity {

	protected Entity thrower;

	public ThrownEntity(World world, int id, float width, float height, Entity thrower) {
		super(world, id, width, height);
		this.thrower = thrower;
	}
	
	public ThrownEntity(World world, int id, float width, float height) {
		this(world, id, width, height, null);
	}

	public Entity getThrower() {
		return thrower;
	}

	public void setThrower(Entity thrower) {
		this.thrower = thrower;
	}
}