package org.topdank.mc.bot.api.entity.object.item;

import java.util.Map;

import org.topdank.mc.bot.api.entity.object.ObjectEntity;
import org.topdank.mc.bot.api.world.World;

public class EnderCrystalEntity extends ObjectEntity {

	private int health;

	public EnderCrystalEntity(World world, int id) {
		super(world, id, 2F, 2F);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(8)) {
			setHealth((int) metadata.get(8));
		}
	}
}