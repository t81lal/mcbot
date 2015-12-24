package org.topdank.mc.bot.impl.entity;

import java.util.Map;

import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.world.World;

public class ArrowEntity extends Entity {

	private Entity shooter;
	private boolean isCrit;

	public ArrowEntity(World world, int id) {
		super(world, id, 0.5F, 0.5F);
	}

	public Entity getShooter() {
		return shooter;
	}

	public void setShooter(Entity shooter) {
		this.shooter = shooter;
	}

	public boolean isCritical() {
		return isCrit;
	}

	public void setCritical(boolean isCrit) {
		this.isCrit = isCrit;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			setCritical((byte) metadata.get(16) != 0);
		}
	}
}