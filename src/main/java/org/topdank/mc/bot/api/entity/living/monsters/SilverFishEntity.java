package org.topdank.mc.bot.api.entity.living.monsters;

import java.util.Map;

import org.topdank.mc.bot.api.entity.living.LivingEntity;
import org.topdank.mc.bot.api.world.World;

public class SilverFishEntity extends LivingEntity {

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