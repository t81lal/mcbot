package org.topdank.mc.bot.impl.entity;

import java.util.Map;

import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.item.ItemStack;
import org.topdank.mc.bot.api.world.World;

public class ItemEntity extends Entity {

	private ItemStack item;

	public ItemEntity(World world, int id) {
		super(world, id, 0.25F, 0.25F);
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(10)) {
			setItem((ItemStack) metadata.get(10));
		}
	}
}