package org.topdank.mc.bot.impl.entity.impl.living.creature.ageable;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.impl.entity.impl.living.creature.CreatureEntity;

public abstract class AgeableEntity extends CreatureEntity {

	protected int growthTimer;

	public AgeableEntity(World world, int id, float width, float height) {
		super(world, id, width, height);
	}

	public int getGrowthTimer() {
		return growthTimer;
	}

	public void setGrowthTimer(int growthTimer) {
		this.growthTimer = growthTimer;
	}	
	
	public boolean isChild() {
		return getGrowthTimer() < 0;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(12)) {
			setGrowthTimer((byte) metadata.get(12));
		}
	}
}