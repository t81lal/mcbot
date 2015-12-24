package org.topdank.mc.bot.impl.entity.impl.living.creature.mob;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class EnderDragonEntity extends MobEntity {

	public EnderDragonEntity(World world, int id) {
		super(world, id, 16F, 8F);
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		// System.out.println("EnderDragon got:");
		// for (int i : metadata.keySet()) {
		// System.out.println("enderdragon: " + i + " " + metadata.get(i));
		// }
	}
}