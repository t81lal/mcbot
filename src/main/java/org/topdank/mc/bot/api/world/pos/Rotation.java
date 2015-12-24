package org.topdank.mc.bot.api.world.pos;

public class Rotation {

	protected final float x;
	protected final float y;
	protected final float z;

	public Rotation(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public float getZ() {
		return this.z;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Rotation)) {
			return false;
		} else {
			Rotation r = (Rotation) other;
			return this.x == r.x && this.y == r.y && this.z == r.z;
		}
	}
}