package org.topdank.mc.bot.impl.entity.impl.projectile;

import org.topdank.mc.bot.api.item.BasicItemStack;
import org.topdank.mc.bot.api.item.ItemStack;
import org.topdank.mc.bot.api.world.World;

public class PotionEntity extends ProjectileEntity {

	private ItemStack damage;

	public PotionEntity(World world, int id) {
		super(world, id, 0.25F, 0.25F);
	}

	public ItemStack getDamage() {
		return damage;
	}

	public void setDamage(ItemStack damage) {
		this.damage = damage;
	}
	
	public void setPotionType(int type) {
		setDamage(new BasicItemStack(-1, 1, type));
	}
}