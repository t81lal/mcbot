package org.topdank.mc.bot.impl.entity.living.creature.mob;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class SilverFishEntity extends MobEntity {

	public SilverFishEntity(World world, int id) {
		super(world, id, 0.4F, 0.3F);
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		System.out.println("SilverFish got:");
		for (int i : metadata.keySet()) {
			System.out.println("silverfish: " + i + " " + metadata.get(i));
		}
	}
}