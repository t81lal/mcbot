package org.topdank.mc.bot.api.entity.living.ageable.tameable;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class WolfEntity extends TameableEntity {

	private boolean isAngry;
	private boolean isBegging;
	private int collarColour;

	public WolfEntity(World world, int id) {
		super(world, id, 0.6F, 0.8F);
	}

	public boolean isAngry() {
		return isAngry;
	}

	public void setAngry(boolean isAngry) {
		this.isAngry = isAngry;
	}

	public boolean isBegging() {
		return isBegging;
	}

	public void setBegging(boolean isBegging) {
		this.isBegging = isBegging;
	}

	public int getCollarColour() {
		return collarColour;
	}

	public void setCollarColour(int collarColour) {
		this.collarColour = collarColour;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			byte flags = (byte) metadata.get(16);
			setAngry((flags & 0x02) != 0);
		}

		if (metadata.containsKey(18)) {
			setHealth((float) metadata.get(18));
		}

		if (metadata.containsKey(20)) {
			setCollarColour((byte) metadata.get(20));
		}
	}
}