package org.topdank.mc.bot.impl.entity.impl.fireball;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class WitherSkullEntity extends FireBallEntity {

	private boolean isInvulnerable;

	public WitherSkullEntity(World world, int id) {
		super(world, id, 0.3125F, 0.3125F);
	}

	public boolean isInvulnerable() {
		return isInvulnerable;
	}

	public void setInvulnerable(boolean isInvulnerable) {
		this.isInvulnerable = isInvulnerable;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);
		if (metadata.containsKey(10)) {
			isInvulnerable = ((byte) metadata.get(10)) == 1;
		}
	}
}