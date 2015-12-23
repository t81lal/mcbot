package org.topdank.mc.bot.api.entity.living.monsters;

import java.util.Map;

import org.topdank.mc.bot.api.entity.living.LivingEntity;
import org.topdank.mc.bot.api.world.World;

public class CreeperEntity extends LivingEntity {

	private CreeperState state;
	private boolean isSuperCharged;

	public CreeperEntity(World world, int id) {
		super(world, id, 0.6F, 1.8F);
	}

	public CreeperState getState() {
		return state;
	}

	public void setState(CreeperState state) {
		this.state = state;
	}

	public boolean isSuperCharged() {
		return isSuperCharged;
	}

	public void setSuperCharged(boolean isSuperCharged) {
		this.isSuperCharged = isSuperCharged;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			// setState(CreeperState.values()[(byte) metadata.get(16)]);
		}

		if (metadata.containsKey(17)) {
			setSuperCharged((byte) metadata.get(17) != 0);
		}
	}

	public enum CreeperState {
		IDLE,
		FUSED;
	}
}