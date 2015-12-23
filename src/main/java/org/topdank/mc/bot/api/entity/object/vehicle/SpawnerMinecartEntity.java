package org.topdank.mc.bot.api.entity.object.vehicle;

import org.topdank.mc.bot.api.world.World;

public class SpawnerMinecartEntity extends MinecartEntity {

	private byte minimumSpawnDelay;
	
	public SpawnerMinecartEntity(World world, int id) {
		super(world, id, MinecartType.SPAWNER);
	}

	public byte getMinimumSpawnDelay() {
		return minimumSpawnDelay;
	}

	public void setMinimumSpawnDelay(byte minimumSpawnDelay) {
		this.minimumSpawnDelay = minimumSpawnDelay;
	}
	
	@Override
	public void updateStatus(int id) {
		super.updateStatus(id);
		minimumSpawnDelay = (byte) id;
	}
}