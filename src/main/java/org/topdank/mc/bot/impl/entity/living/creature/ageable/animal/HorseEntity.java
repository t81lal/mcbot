package org.topdank.mc.bot.impl.entity.living.creature.ageable.animal;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.impl.entity.living.creature.ageable.AgeableEntity;

public class HorseEntity extends AgeableEntity {

	private boolean isTame;
	private boolean hasSaddle;
	private boolean hasChest;
	private boolean isBred;
	private boolean isEating;
	private boolean isRearing;
	private boolean isMouthOpen;

	private HorseType type;
	private HorseColour colour;
	private HorseStyle style;
	private HorseArmour armour;

	private String owner;

	public HorseEntity(World world, int id) {
		super(world, id, 1.4F, 1.6F);
	}

	public boolean isTame() {
		return isTame;
	}

	public void setTame(boolean isTame) {
		this.isTame = isTame;
	}

	public boolean isHasSaddle() {
		return hasSaddle;
	}

	public void setHasSaddle(boolean hasSaddle) {
		this.hasSaddle = hasSaddle;
	}

	public boolean isHasChest() {
		return hasChest;
	}

	public void setHasChest(boolean hasChest) {
		this.hasChest = hasChest;
	}

	public boolean isBred() {
		return isBred;
	}

	public void setBred(boolean isBred) {
		this.isBred = isBred;
	}

	public boolean isEating() {
		return isEating;
	}

	public void setEating(boolean isEating) {
		this.isEating = isEating;
	}

	public boolean isRearing() {
		return isRearing;
	}

	public void setRearing(boolean isRearing) {
		this.isRearing = isRearing;
	}

	public boolean isMouthOpen() {
		return isMouthOpen;
	}

	public void setMouthOpen(boolean isMouthOpen) {
		this.isMouthOpen = isMouthOpen;
	}

	public HorseType getType() {
		return type;
	}

	public void setType(HorseType type) {
		this.type = type;
	}

	public HorseColour getColour() {
		return colour;
	}

	public void setColour(HorseColour colour) {
		this.colour = colour;
	}

	public HorseStyle getStyle() {
		return style;
	}

	public void setStyle(HorseStyle style) {
		this.style = style;
	}

	public HorseArmour getArmour() {
		return armour;
	}

	public void setArmour(HorseArmour armour) {
		this.armour = armour;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			int flags = (int) metadata.get(16);
			setTame((flags & 0x02) != 0);
			setHasSaddle((flags & 0x04) != 0);
			setHasChest((flags & 0x08) != 0);
			setBred((flags & 0x10) != 0);
			setEating((flags & 0x20) != 0);
			setRearing((flags & 0x40) != 0);
			setMouthOpen((flags & 0x80) != 0);
		}

		if (metadata.containsKey(19)) {
			type = HorseType.values()[(byte) metadata.get(19)];
		}

		if (metadata.containsKey(20)) {
			int flags = (int) metadata.get(20);

			int colourIndex = flags & 0x00FF;
			int styleIndex = flags & 0xFF00;

			colour = HorseColour.values()[colourIndex];
			style = HorseStyle.values()[styleIndex];
		}

		if (metadata.containsKey(21)) {
			owner = (String) metadata.get(21);
		}

		if (metadata.containsKey(22)) {
			armour = HorseArmour.values()[(int) metadata.get(22)];
		}
	}

	public enum HorseType {
		HORSE,
		DONKEY,
		MULE,
		ZOMBIE,
		SKELETON;
	}

	public enum HorseColour {
		WHITE,
		CREAM,
		CHESTNUT,
		BROWN,
		BLACK,
		GREY,
		DARK_DOWN;
	}

	public enum HorseStyle {
		NONE,
		WHITE,
		WHITEFIELD,
		WHITE_DOTS,
		BLACK_DOTS;
	}

	public enum HorseArmour {
		NONE,
		IRON,
		GOLD,
		DIAMOND;
	}
}