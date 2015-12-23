package org.topdank.mc.bot.api.entity.living.ageable;

import java.util.Map;

import org.topdank.mc.bot.api.entity.living.LivingEntity;
import org.topdank.mc.bot.api.world.World;

public class AgeableEntity extends LivingEntity {

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

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(12)) {
			setGrowthTimer((byte) metadata.get(12));
		}
	}
}