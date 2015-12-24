package org.topdank.mc.bot.impl.entity.impl;

import java.util.Map;

import org.topdank.mc.bot.api.entity.accessors.IArrow;
import org.topdank.mc.bot.api.entity.accessors.IEntity;
import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.impl.entity.Entity;

public class ArrowEntity extends Entity implements IArrow {

	private IEntity shooter;
	private boolean isCrit;

	public ArrowEntity(World world, int id) {
		super(world, id, 0.5F, 0.5F);
	}

	@Override
	public IEntity getShooter() {
		return shooter;
	}

	@Override
	public void setShooter(IEntity shooter) {
		this.shooter = shooter;
	}

	@Override
	public boolean isCritical() {
		return isCrit;
	}

	@Override
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