package org.topdank.mc.bot.api.entity.living;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class ArmourStandEntity extends LivingEntity {

	public ArmourStandEntity(World world, int id) {
		super(world, id, 0.5F, 1.975F);
	}
	
	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);
		
		System.out.println("meta: " + metadata);
	}
}