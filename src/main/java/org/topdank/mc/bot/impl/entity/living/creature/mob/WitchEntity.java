package org.topdank.mc.bot.impl.entity.living.creature.mob;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class WitchEntity extends MobEntity {

	private boolean isAggressive;

	public WitchEntity(World world, int id) {
		super(world, id, 0.6F, 1.95F);
	}

	public boolean isAggressive() {
		return isAggressive;
	}

	public void setAggressive(boolean isAggressive) {
		this.isAggressive = isAggressive;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(21)) {
			// according to src
			setAggressive((byte) metadata.get(21) == 1);
		}
	}
}