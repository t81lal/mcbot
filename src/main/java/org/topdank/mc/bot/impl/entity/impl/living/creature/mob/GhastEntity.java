package org.topdank.mc.bot.impl.entity.impl.living.creature.mob;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class GhastEntity extends MobEntity {

	private boolean isAttacking;

	public GhastEntity(World world, int id) {
		super(world, id, 4F, 4F);
	}

	public boolean isAttacking() {
		return isAttacking;
	}

	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			setAttacking((byte) metadata.get(16) != 0);
		}
	}
}