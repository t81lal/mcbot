package org.topdank.mc.bot.api.entity.living.monsters;

import java.util.Map;

import org.topdank.mc.bot.api.entity.living.LivingEntity;
import org.topdank.mc.bot.api.world.World;

public class IronGolemEntity extends LivingEntity {

	private boolean isPlayerCreated;

	public IronGolemEntity(World world, int id) {
		super(world, id, 1.4F, 2.9F);
	}

	public boolean isPlayerCreated() {
		return isPlayerCreated;
	}

	public void setPlayerCreated(boolean isPlayerCreated) {
		this.isPlayerCreated = isPlayerCreated;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			setPlayerCreated((byte) metadata.get(16) != 0);
		}
	}
}