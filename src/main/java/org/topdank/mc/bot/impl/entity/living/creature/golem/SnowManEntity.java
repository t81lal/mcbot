package org.topdank.mc.bot.impl.entity.living.creature.golem;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class SnowManEntity extends GolemEntity {

	public SnowManEntity(World world, int id) {
		super(world, id, 0.7F, 1.9F);
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		System.out.println("SnowMan got:");
		for (int i : metadata.keySet()) {
			System.out.println("snowman: " + i + " " + metadata.get(i));
		}
	}
}