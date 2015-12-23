package org.topdank.mc.bot.api.entity.object.item;

import org.topdank.mc.bot.api.entity.object.ObjectEntity;
import org.topdank.mc.bot.api.world.World;

public class FallingBlockEntity extends ObjectEntity {

	private int blockId;
	private int blockMeta;
	
	public FallingBlockEntity(World world, int id, int blockId, int blockMeta) {
		super(world, id, 1F, 1F);
		this.blockId = blockId;
		this.blockMeta = blockMeta;
	}

	public int getBlockId() {
		return blockId;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}

	public int getBlockMeta() {
		return blockMeta;
	}

	public void setBlockMeta(int blockMeta) {
		this.blockMeta = blockMeta;
	}
}