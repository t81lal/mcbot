package org.topdank.mc.bot.impl.entity.impl.living.creature.mob;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class SpiderEntity extends MobEntity {

	private boolean isClimbing;

	public SpiderEntity(World world, int id, float width, float height) {
		super(world, id, width, height);
	}

	public SpiderEntity(World world, int id) {
		super(world, id, 1.4F, 0.9F);
	}

	public boolean isClimbing() {
		return isClimbing;
	}

	public void setClimbing(boolean isClimbing) {
		this.isClimbing = isClimbing;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			setClimbing(((byte) metadata.get(16) & 1) != 0);
		}
	}
}