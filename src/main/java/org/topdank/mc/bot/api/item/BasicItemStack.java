package org.topdank.mc.bot.api.item;

import org.topdank.mc.bot.api.nbt.tag.builtin.CompoundTag;

public class BasicItemStack implements ItemStack {
	
	private int id, stackSize, damage;
	private CompoundTag stackTagCompound;
	
	public BasicItemStack(int id, int stackSize, int damage, CompoundTag stackTagCompound) {
		this.id = id;
		this.stackSize = stackSize;
		this.damage = damage;
		this.stackTagCompound = stackTagCompound;
	}
	
	public BasicItemStack(int id, int stackSize, int damage) {
		this.id = id;
		this.stackSize = stackSize;
		this.damage = damage;
	}
	
	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public int getStackSize() {
		return stackSize;
	}
	
	@Override
	public void setStackSize(int stackSize) {
		this.stackSize = stackSize;
	}
	
	@Override
	public int getDamage() {
		return damage;
	}
	
	@Override
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	@Override
	public CompoundTag getStackTagCompound() {
		return stackTagCompound;
	}
	
	@Override
	public void setStackTagCompound(CompoundTag stackTagCompound) {
		this.stackTagCompound = stackTagCompound;
	}
	
	@Override
	public ItemStack clone() {
		return new BasicItemStack(id, stackSize, damage);
	}
	
	@Override
	public String toString() {
		return "ItemStack[" + id + ":" + damage + "x" + stackSize + "]";
	}
}