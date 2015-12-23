package org.topdank.mc.bot.api.entity.living.ageable;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class BatEntity extends AgeableEntity {

	private boolean isHanging;

	public BatEntity(World world, int id) {
		super(world, id, 0.5F, 0.9F);
	}

	public boolean isHanging() {
		return isHanging;
	}

	public void setHanging(boolean isHanging) {
		this.isHanging = isHanging;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			setHanging((byte) metadata.get(16) == 1);
		}
	}
}