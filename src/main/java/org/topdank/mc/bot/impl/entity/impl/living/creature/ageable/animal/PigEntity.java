package org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.AgeableEntity;

public class PigEntity extends AgeableEntity {

	private boolean hasSaddle;

	public PigEntity(World world, int id) {
		super(world, id, 0.9F, 0.9F);
	}

	public boolean isHasSaddle() {
		return hasSaddle;
	}

	public void setHasSaddle(boolean hasSaddle) {
		this.hasSaddle = hasSaddle;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			setHasSaddle((byte) metadata.get(16) != 0);
		}
	}
}