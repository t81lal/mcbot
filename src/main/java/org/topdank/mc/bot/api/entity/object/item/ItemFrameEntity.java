package org.topdank.mc.bot.api.entity.object.item;

import java.util.Map;

import org.topdank.mc.bot.api.entity.object.ObjectEntity;
import org.topdank.mc.bot.api.item.ItemStack;
import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.api.world.pos.Direction;

public class ItemFrameEntity extends ObjectEntity {

	private ItemStack item;
	private Direction rotation;

	public ItemFrameEntity(World world, int id) {
		super(world, id, 0.375F, 0.03125F);
	}

	// 8 Slot Item
	// 9 Byte Rotation

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}

	public Direction getRotation() {
		return rotation;
	}

	public void setRotation(Direction rotation) {
		this.rotation = rotation;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(8)) {
			setItem((ItemStack) metadata.get(8));
		}

		if (metadata.containsKey(9)) {
			System.out.println("ItemFrame rotation: " + metadata.get(9));
		}
	}
}
