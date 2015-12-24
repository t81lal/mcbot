package org.topdank.mc.bot.impl.entity.impl.minecart;

import org.topdank.mc.bot.api.world.World;

public class TNTMinecartEntity extends MinecartEntity {

	private boolean ignited;
	
	public TNTMinecartEntity(World world, int id) {
		super(world, id, MinecartType.TNT);
		ignited = false;
	}
	
	public boolean isIgnited() {
		return ignited;
	}
	
	void ignite() {
		ignited = true;
	}
	
	@Override
	public void updateStatus(int id) {
		super.updateStatus(id);
		if(id == 10) {
			ignite();
		}
	}
}