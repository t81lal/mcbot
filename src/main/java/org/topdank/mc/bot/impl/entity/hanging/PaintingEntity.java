package org.topdank.mc.bot.impl.entity.hanging;

import org.topdank.mc.bot.api.world.Painting;
import org.topdank.mc.bot.api.world.World;

public class PaintingEntity extends HangingEntity {

	private Painting painting;

	public PaintingEntity(World world, int id, Painting p) {
		super(world, id, 1F, 1F);
		setPainting(p);
	}

	public Painting getPainting() {
		return painting;
	}

	public void setPainting(Painting p) {
		painting = p;
		setWidth(p.sizeX / 16);
		setHeight(p.sizeY / 16);
	}
}