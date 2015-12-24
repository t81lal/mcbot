package org.topdank.mc.bot.impl.entity.living.creature.mob;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class CreeperEntity extends MobEntity {

	private CreeperState state;
	private boolean isSuperCharged;
	private boolean isIgnited;

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
	
	public boolean isIgnited() {
		return isIgnited;
	}

	public void setIgnited(boolean isIgnited) {
		this.isIgnited = isIgnited;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			byte state = (byte) metadata.get(16);
			if(state == -1) {
				setState(CreeperState.IDLE);
			} else if(state == 1) {
				setState(CreeperState.FUSED);
			} else {
				throw new UnsupportedOperationException("State: " + state);
			}
		}

		if (metadata.containsKey(17)) {
			setSuperCharged((byte) metadata.get(17) != 0);
		}
		
		if(metadata.containsKey(18)) {
			setIgnited((byte) metadata.get(18) != 0);
		}
	}

	public enum CreeperState {
		IDLE,
		FUSED;
	}
}