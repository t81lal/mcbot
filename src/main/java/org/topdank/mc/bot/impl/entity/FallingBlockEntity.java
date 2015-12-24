package org.topdank.mc.bot.impl.entity;

import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.world.World;

public class FallingBlockEntity extends Entity {

	private int blockId;
	private int blockMeta;
	
	public FallingBlockEntity(World world, int id, int blockId, int blockMeta) {
		super(world, id, 0.98F, 0.98F);
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