package org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.AgeableEntity;

public class SheepEntity extends AgeableEntity {

	private AnimalColour colour;
	private boolean isSheared;

	public SheepEntity(World world, int id) {
		super(world, id, 0.9F, 1.3F);
	}

	public AnimalColour getColour() {
		return colour;
	}

	public void setColour(AnimalColour colour) {
		this.colour = colour;
	}

	public boolean isSheared() {
		return isSheared;
	}

	public void setSheared(boolean isSheared) {
		this.isSheared = isSheared;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			byte flags = (byte) metadata.get(16);
			setColour(AnimalColour.values()[flags & 0x0F]);
			setSheared((flags & 0x10) != 0);
		}
	}

	@Override
	public void updateStatus(int id) {
		if(id == 10) {
			// head going down to eat 
		} else {
			super.updateStatus(id);
		}
	}
}