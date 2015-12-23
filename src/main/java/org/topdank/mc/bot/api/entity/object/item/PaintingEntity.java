package org.topdank.mc.bot.api.entity.object.item;

import org.topdank.mc.bot.api.entity.object.ObjectEntity;
import org.topdank.mc.bot.api.world.Painting;
import org.topdank.mc.bot.api.world.World;

public class PaintingEntity extends ObjectEntity {

	private Painting painting;

	public PaintingEntity(World world, int id, Painting p) {
		super(world, id, p.sizeX / 16, p.sizeY / 16);
		painting = p;
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