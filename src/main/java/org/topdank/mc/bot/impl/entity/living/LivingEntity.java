package org.topdank.mc.bot.impl.entity.living;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public abstract class LivingEntity extends BaseLivingEntity {

	protected boolean hasNoAI;

	public LivingEntity(World world, int id, float width, float height) {
		super(world, id, width, height);
	}
	
	public boolean isHasNoAI() {
		return hasNoAI;
	}

	public void setHasNoAI(boolean hasNoAI) {
		this.hasNoAI = hasNoAI;
	}

	// 15 Byte Whether the entity has no ai.

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(15)) {
			setHasNoAI((byte) metadata.get(15) == 1);
		}
	}
}