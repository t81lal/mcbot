package org.topdank.mc.bot.api.entity.living.monsters;

import java.util.Map;

import org.topdank.mc.bot.api.entity.living.LivingEntity;
import org.topdank.mc.bot.api.world.World;

public class WitchEntity extends LivingEntity {

	private boolean isAggro;

	public WitchEntity(World world, int id) {
		super(world, id, 0.6F, 1.95F);
	}

	public boolean isAggro() {
		return isAggro;
	}

	public void setAggro(boolean isAggro) {
		this.isAggro = isAggro;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(21)) {
			setAggro((byte) metadata.get(21) != 0);
		}
	}
}