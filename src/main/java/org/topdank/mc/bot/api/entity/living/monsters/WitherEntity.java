package org.topdank.mc.bot.api.entity.living.monsters;

import java.util.Map;

import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.entity.living.LivingEntity;
import org.topdank.mc.bot.api.world.World;

public class WitherEntity extends LivingEntity {

	private Entity watchedEntity1;
	private Entity watchedEntity2;
	private Entity watchedEntity3;

	private int invulerabilityTime;

	public WitherEntity(World world, int id) {
		super(world, id, 0.9F, 3.5F);
	}

	public Entity getWatchedEntity1() {
		return watchedEntity1;
	}

	public void setWatchedEntity1(Entity watchedEntity1) {
		this.watchedEntity1 = watchedEntity1;
	}

	public Entity getWatchedEntity2() {
		return watchedEntity2;
	}

	public void setWatchedEntity2(Entity watchedEntity2) {
		this.watchedEntity2 = watchedEntity2;
	}

	public Entity getWatchedEntity3() {
		return watchedEntity3;
	}

	public void setWatchedEntity3(Entity watchedEntity3) {
		this.watchedEntity3 = watchedEntity3;
	}

	public int getInvulerabilityTime() {
		return invulerabilityTime;
	}

	public void setInvulerabilityTime(int invulerabilityTime) {
		this.invulerabilityTime = invulerabilityTime;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(17)) {
			setWatchedEntity1(resolveTarget((int) metadata.get(17)));
		}

		if (metadata.containsKey(18)) {
			setWatchedEntity2(resolveTarget((int) metadata.get(18)));
		}

		if (metadata.containsKey(19)) {
			setWatchedEntity3(resolveTarget((int) metadata.get(19)));
		}

		if (metadata.containsKey(20)) {
			setInvulerabilityTime((int) metadata.get(20));
		}
	}

	private Entity resolveTarget(int id) {
		return world.getEntityById(id);
	}
}