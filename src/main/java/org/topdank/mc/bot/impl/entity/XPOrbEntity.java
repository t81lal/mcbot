package org.topdank.mc.bot.impl.entity;

import java.util.Map;

import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.world.World;

public class XPOrbEntity extends Entity {

	public XPOrbEntity(World world, int id) {
		super(world, id, 0.25F, 0.25F);
	}
	
	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);
		
		System.out.println(metadata);
	}
}