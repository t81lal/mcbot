package org.topdank.mc.bot.api.entity.tile;

import java.util.Arrays;

import org.topdank.mc.bot.api.world.World;

public class SignEntity extends TileEntity {

	private String[] text;

	public SignEntity(World world) {
		super(world);
	}

	public SignEntity(World world, String[] text) {
		super(world);
		if (text.length == 4)
			this.text = text;
		else
			throw new IllegalArgumentException("Received text " + Arrays.toString(text));
	}

	public String[] getText() {
		return text;
	}

	public void setText(String[] text) {
		if (text.length == 4) {
			this.text = text;
		}
	}
}