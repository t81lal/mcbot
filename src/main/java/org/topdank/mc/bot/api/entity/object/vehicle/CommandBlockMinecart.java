package org.topdank.mc.bot.api.entity.object.vehicle;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class CommandBlockMinecart extends MinecartEntity {

	public CommandBlockMinecart(World world, int id) {
		super(world, id, MinecartType.COMMAND_BLOCK);
	}
	
	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);
		
		System.out.println("Minecart metadata: " + metadata);
		
		if(metadata.containsKey(23)) {
			System.out.println("23: " + metadata.get(23));
		}
		if(metadata.containsKey(24)) {
			System.out.println("24: " + metadata.get(24));
		}
	}
}