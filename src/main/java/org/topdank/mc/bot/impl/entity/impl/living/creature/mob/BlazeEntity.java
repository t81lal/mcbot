package org.topdank.mc.bot.impl.entity.impl.living.creature.mob;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class BlazeEntity extends MobEntity {

	private boolean isFlaming;

	public BlazeEntity(World world, int id) {
		super(world, id, 0.6F, 1.8F);
	}

	public boolean isFlaming() {
		return isFlaming;
	}

	public void setFlaming(boolean isFlaming) {
		this.isFlaming = isFlaming;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			setFlaming(((byte) metadata.get(16) & 1) != 0);
		}
	}
}