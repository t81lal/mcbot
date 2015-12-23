package org.topdank.mc.bot.api.entity.living.monsters;

import java.util.Map;

import org.topdank.mc.bot.api.entity.living.LivingEntity;
import org.topdank.mc.bot.api.world.World;

public class GuardianEntity extends LivingEntity {

	public GuardianEntity(World world, int id) {
		super(world, id, 0.85F, 0.85F);
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		System.out.println("Guardian got:");
		for (int i : metadata.keySet()) {
			System.out.println("guardian: " + i + " " + metadata.get(i));
		}
	}
}