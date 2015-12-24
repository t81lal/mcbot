package org.topdank.mc.bot.impl.entity.living.creature.mob;

import org.topdank.mc.bot.api.world.World;

public class SquidEntity extends MobEntity {

	public SquidEntity(World world, int id) {
		super(world, id, 0.95F, 0.95F);
	}
	
	@Override
	public void updateStatus(int id) {
		if(id == 19) {
			// reset squad rotation
		} else {
			super.updateStatus(id);
		}
	}
}