package org.topdank.mc.bot.api.item.inventory;

import org.topdank.mc.bot.api.item.ItemStack;

public interface Inventory {
	
	int getSize();
	
	ItemStack getItemAt(int slot);
	
	void setItemAt(int slot, ItemStack item);
	
	void setItemFromServerAt(int serverSlot, ItemStack item);
	
	void selectItemAt(int slot, boolean leftClick);
	
	void selectItemAtWithShift(int slot);
	
	ItemStack getSelectedItem();
	
	void dropSelectedItem();
	
	void close();
	
	int getWindowId();
}