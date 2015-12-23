package org.topdank.mc.bot.api.entity.living.ageable;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class SheepEntity extends AgeableEntity {

	private SheepColour colour;
	private boolean isSheared;

	public SheepEntity(World world, int id) {
		super(world, id, 0.9F, 1.3F);
	}

	public SheepColour getColour() {
		return colour;
	}

	public void setColour(SheepColour colour) {
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
			setColour(SheepColour.values()[flags & 0x0F]);
			setSheared((flags & 0x10) != 0);
		}
	}

	public enum SheepColour {
		WHITE,
		ORANGE,
		MAGENTA,
		LIGHT_BLUE,
		YELLOW,
		LIME,
		PINK,
		GREY,
		SILVER,
		CYAN,
		PURPLE,
		BLUE,
		BROWN,
		GREEN,
		RED,
		BLACK;
	}
}