package org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.AgeableEntity;

public class RabbitEntity extends AgeableEntity {

	// TODO: find out rabbit types
	// private int type;
	private boolean isEvil;
	
	public RabbitEntity(World world, int id) {
		super(world, id, 0.6F, 0.7F);
	}

	public boolean isEvil() {
		return isEvil;
	}

	public void setEvil(boolean b) {
		this.isEvil = b;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(18)) {
			setEvil((int) metadata.get(18) == 99);
		}
	}
	
	@Override
	public void updateStatus(int id) {
		if(id == 1) {
			// running particles
		} else {
			super.updateStatus(id);
		}
	}
}