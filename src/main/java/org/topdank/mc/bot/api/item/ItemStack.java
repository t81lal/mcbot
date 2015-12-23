package org.topdank.mc.bot.api.item;

import org.topdank.mc.bot.api.nbt.tag.builtin.CompoundTag;

public interface ItemStack extends Cloneable {
	
	int getId();
	
	int getStackSize();
	
	void setStackSize(int stackSize);
	
	int getDamage();
	
	void setDamage(int damage);
	
	CompoundTag getStackTagCompound();
	
	void setStackTagCompound(CompoundTag compound);
	
	ItemStack clone();
}