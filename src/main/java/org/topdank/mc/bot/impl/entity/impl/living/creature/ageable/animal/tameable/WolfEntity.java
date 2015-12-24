package org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal.tameable;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal.AnimalColour;

public class WolfEntity extends TameableEntity {

	private boolean isAngry;
	private boolean isBegging;
	private AnimalColour collarColour;

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

	public AnimalColour getCollarColour() {
		return collarColour;
	}

	public void setCollarColour(AnimalColour collarColour) {
		this.collarColour = collarColour;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			setAngry(((byte) metadata.get(16) & 0x02) != 0);
		}

		if (metadata.containsKey(18)) {
			setHealth((float) metadata.get(18));
		}
		if(metadata.containsKey(19)) {
			// according to the source
			setBegging((byte) metadata.get(19) == 1);
		}

		if (metadata.containsKey(20)) {
			setCollarColour(AnimalColour.values()[(byte) metadata.get(20) & 15]);
		}
	}
}