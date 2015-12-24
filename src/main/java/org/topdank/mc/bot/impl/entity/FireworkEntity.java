package org.topdank.mc.bot.impl.entity;

import java.util.Map;

import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.item.ItemStack;
import org.topdank.mc.bot.api.world.World;

public class FireworkEntity extends Entity {

	private ItemStack data;
	private boolean exploded;

	public FireworkEntity(World world, int id) {
		super(world, id, 0.25F, 0.25F);
	}

	public ItemStack getData() {
		return data;
	}

	public void setData(ItemStack data) {
		this.data = data;
	}

	public boolean isExploded() {
		return exploded;
	}

	public void setExploded(boolean exploded) {
		this.exploded = exploded;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(8)) {
			setData((ItemStack) metadata.get(8));
		}
	}
	
	@Override
	public void updateStatus(int id) {
		if(id == 17) {
			exploded = true;
		} else {
			super.updateStatus(id);
		}
	}
}