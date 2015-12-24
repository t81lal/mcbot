package org.topdank.mc.bot.impl.entity;

import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.impl.entity.living.LivingEntity;

public class PrimedTNTEntity extends Entity {

	private int fuse;
	private LivingEntity placer;

	public PrimedTNTEntity(World world, int id) {
		super(world, id, 0.98F, 0.98F);
	}

	public int getFuse() {
		return fuse;
	}

	public void setFuse(int fuse) {
		this.fuse = fuse;
	}

	public LivingEntity getPlacer() {
		return placer;
	}

	public void setPlacer(LivingEntity placer) {
		this.placer = placer;
	}
}