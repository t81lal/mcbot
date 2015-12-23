package org.topdank.mc.bot.api.entity.object.item;

import java.util.Map;

import org.topdank.mc.bot.api.entity.object.ObjectEntity;
import org.topdank.mc.bot.api.world.World;

public class XPOrbEntity extends ObjectEntity {

	public XPOrbEntity(World world, int id) {
		super(world, id, 0.25F, 0.25F);
	}
	
	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);
		
		System.out.println(metadata);
	}
}