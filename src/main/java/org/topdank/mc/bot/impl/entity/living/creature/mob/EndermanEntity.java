package org.topdank.mc.bot.impl.entity.living.creature.mob;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class EndermanEntity extends MobEntity {

	private int carriedBlockId;
	private int carriedBlockData;
	private boolean isScreaming;

	public EndermanEntity(World world, int id) {
		super(world, id, 0.6F, 2.9F);
	}

	public int getCarriedBlockId() {
		return carriedBlockId;
	}

	public void setCarriedBlockId(int carriedBlockId) {
		this.carriedBlockId = carriedBlockId;
	}

	public int getCarriedBlockData() {
		return carriedBlockData;
	}

	public void setCarriedBlockData(int carriedBlockData) {
		this.carriedBlockData = carriedBlockData;
	}

	public boolean isScreaming() {
		return isScreaming;
	}

	public void setScreaming(boolean isScreaming) {
		this.isScreaming = isScreaming;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			setCarriedBlockId((short) metadata.get(16) & 65535);
		}

		if (metadata.containsKey(17)) {
			// ??
			setCarriedBlockData((byte) metadata.get(17));
		}

		if (metadata.containsKey(18)) {
			// according to src
			setScreaming((byte) metadata.get(18) > 0);
		}
	}
}