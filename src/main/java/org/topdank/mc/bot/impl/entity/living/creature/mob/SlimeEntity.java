package org.topdank.mc.bot.impl.entity.living.creature.mob;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class SlimeEntity extends MobEntity {

	public static final float SLIME_UNIT = 0.51000005F;

	private int size;

	public SlimeEntity(World world, int id) {
		this(world, id, 1F);
	}

	public SlimeEntity(World world, int id, float scale) {
		super(world, id, SLIME_UNIT * scale, SLIME_UNIT * scale);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
		setSize(size * SLIME_UNIT, size * SLIME_UNIT);
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			size = (byte) metadata.get(16);
		}
	}
}