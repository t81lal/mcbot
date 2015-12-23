package org.topdank.mc.bot.api.entity.object;

import org.topdank.mc.bot.api.world.World;

public class VehicleEntity extends ObjectEntity {

	protected float damageTaken;

	public VehicleEntity(World world, int id, float width, float height) {
		super(world, id, width, height);
	}

	public float getDamageTaken() {
		return damageTaken;
	}

	public void setDamageTaken(float damageTaken) {
		this.damageTaken = damageTaken;
	}
}