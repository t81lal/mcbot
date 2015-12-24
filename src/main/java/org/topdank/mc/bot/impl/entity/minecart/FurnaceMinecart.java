package org.topdank.mc.bot.impl.entity.minecart;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class FurnaceMinecart extends MinecartEntity {

	private boolean isPowered;

	public FurnaceMinecart(World world, int id) {
		super(world, id, MinecartType.FURNACE);
	}

	public boolean isPowered() {
		return isPowered;
	}

	public void setPowered(boolean isPowered) {
		this.isPowered = isPowered;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			setPowered(((byte) metadata.get(16) & 1) != 0);
		}
	}
}