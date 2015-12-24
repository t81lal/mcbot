package org.topdank.mc.bot.impl.entity.living.creature.mob;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class ZombieEntity extends MobEntity {

	private boolean isChild;
	private boolean isVillager;
	private boolean isConverting;

	public ZombieEntity(World world, int id) {
		super(world, id, 0.6F, 1.95F);
	}

	public ZombieEntity(World world, int id, float scale) {
		super(world, id, 0.6F * scale, 1.95F * scale);
	}

	public boolean isChild() {
		return isChild;
	}

	public void setChild(boolean isChild) {
		this.isChild = isChild;
	}

	public boolean isVillager() {
		return isVillager;
	}

	public void setVillager(boolean isVillager) {
		this.isVillager = isVillager;
	}

	public boolean isConverting() {
		return isConverting;
	}

	public void setConverting(boolean isConverting) {
		this.isConverting = isConverting;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(12)) {
			setChild((byte) metadata.get(12) == 1);
		}

		if (metadata.containsKey(13)) {
			setVillager((byte) metadata.get(13) == 1);
		}

		if (metadata.containsKey(14)) {
			setConverting((byte) metadata.get(14) == 1);
		}
	}
}