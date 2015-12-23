package org.topdank.mc.bot.api.entity.living;

import java.util.Map;

import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.entity.living.player.LocalPlayer;
import org.topdank.mc.bot.api.item.ItemStack;
import org.topdank.mc.bot.api.world.World;

public class LivingEntity extends Entity {

	protected String nametag;

	protected int breathTimer;
	protected int potionEffectColour;
	protected int lodgedArrows;
	protected int animation;

	protected boolean isPotionEffectAmbient;
	protected boolean alwaysShowNametag;
	protected boolean hasNoAI;

	protected float health;

	protected ItemStack[] armor = new ItemStack[4];
	protected ItemStack heldItem = null;

	public LivingEntity(World world, int id, float width, float height) {
		super(world, id, width, height);
	}

	public String getNametag() {
		return nametag;
	}

	public void setNametag(String nametag) {
		this.nametag = nametag;
	}

	@Override
	public int getBreathTimer() {
		return breathTimer;
	}

	@Override
	public void setBreathTimer(int breathTimer) {
		this.breathTimer = breathTimer;
	}

	public int getPotionEffectColour() {
		return potionEffectColour;
	}

	public void setPotionEffectColour(int potionEffectColour) {
		this.potionEffectColour = potionEffectColour;
	}

	public boolean isPotionEffectAmbient() {
		return isPotionEffectAmbient;
	}

	public void setPotionEffectAmbient(boolean isPotionEffectAmbient) {
		this.isPotionEffectAmbient = isPotionEffectAmbient;
	}

	public boolean isAlwaysShowNametag() {
		return alwaysShowNametag;
	}

	public void setAlwaysShowNametag(boolean alwaysShowNametag) {
		this.alwaysShowNametag = alwaysShowNametag;
	}

	public int getLodgedArrows() {
		return lodgedArrows;
	}

	public void setLodgedArrows(int lodgedArrows) {
		this.lodgedArrows = lodgedArrows;
	}

	public boolean isHasNoAI() {
		return hasNoAI;
	}

	public void setHasNoAI(boolean hasNoAI) {
		this.hasNoAI = hasNoAI;
	}

	public int getAnimation() {
		return animation;
	}

	public void setAnimation(int animation) {
		this.animation = animation;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public ItemStack[] getArmor() {
		return armor;
	}

	public void setArmor(ItemStack[] armor) {
		this.armor = armor;
	}

	public ItemStack getHeldItem() {
		return heldItem;
	}

	public void setHeldItem(ItemStack item) {
		setWornItemAt(0, item);
	}

	public ItemStack getWornItemAt(int slot) {
		return slot == 0 ? heldItem : (slot > 0) && (slot <= armor.length) ? armor[slot - 1] : null;
	}

	public void setWornItemAt(int slot, ItemStack item) {
		if (slot == 0)
			heldItem = item;
		else if ((slot > 0) && (slot <= armor.length))
			armor[slot - 1] = item;
	}

	// Index Type Meaning
	// 2 String Name Tag
	// 3 Byte Always Show Name Tag
	// 6 Float Health
	// 7 Int Potion Effect Color
	// 8 Byte Is Potion Effect Ambient
	// 9 Byte Number of Arrows in Entity
	// 15 Byte Whether the entity has no ai.

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(2)) {
			setNametag((String) metadata.get(2));
		}

		if (metadata.containsKey(3)) {
			setAlwaysShowNametag((byte) metadata.get(3) == 1);
		}

		if (metadata.containsKey(6)) {
			if (this instanceof LocalPlayer)
				System.out.println("hp: " + metadata.get(6));
			setHealth((float) metadata.get(6));
		}

		if (metadata.containsKey(7)) {
			setPotionEffectColour((int) metadata.get(7));
		}

		if (metadata.containsKey(8)) {
			setPotionEffectAmbient((byte) metadata.get(8) == 1);
		}

		if (metadata.containsKey(9)) {
			setLodgedArrows((byte) metadata.get(9));
		}

		if (metadata.containsKey(15)) {
			setHasNoAI((byte) metadata.get(15) == 1);
		}
	}

	@Override
	public void updateProperty(Map<String, EntityProperty> properties) {
		super.updateProperty(properties);
		// if (properties.containsKey("generic.maxHealth")) {
		// System.out.println("gen: " +
		// properties.get("generic.maxHealth").getValue());
		// }
	}
}