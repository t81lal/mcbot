package org.topdank.mc.bot.api.entity.living.monsters;

import java.util.Map;

import org.topdank.mc.bot.api.entity.living.LivingEntity;
import org.topdank.mc.bot.api.world.World;

public class SlimeEntity extends LivingEntity {

	public static final float SLIME_BASE_SIZE = 0.51000005F;

	private int size;

	public SlimeEntity(World world, int id) {
		this(world, id, 1F);
	}

	public SlimeEntity(World world, int id, float scale) {
		super(world, id, SLIME_BASE_SIZE * scale, SLIME_BASE_SIZE * scale);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			size = (byte) metadata.get(16);
		}
	}
}