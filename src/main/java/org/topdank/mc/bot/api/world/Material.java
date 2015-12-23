package org.topdank.mc.bot.api.world;

public class Material {

	public static final int FLAG_FLAMMABLE = 0x01;
	public static final int FLAG_OPAQUE = 0x02;
	public static final int FLAG_REQUIRES_TOOL = 0x04;
	public static final int FLAG_SOLID = 0x08;
	public static final int FLAG_LIQUID = 0x10;
	public static final int FLAG_PUSHABLE = 0x20;

	private final String name;
	private final int flags;

	protected Material(String name, int flags) {
		this.name = name;
		this.flags = flags;
	}

	public String getName() {
		return name;
	}

	public boolean containsFlag(int flag) {
		return (flags & flag) == flag;
	}

	public boolean isFlammable() {
		return containsFlag(FLAG_FLAMMABLE);
	}

	public boolean isOpaque() {
		return containsFlag(FLAG_OPAQUE);
	}

	public boolean requiresTool() {
		return containsFlag(FLAG_REQUIRES_TOOL);
	}

	public boolean isSolid() {
		return containsFlag(FLAG_SOLID);
	}

	public boolean isLiquid() {
		return containsFlag(FLAG_LIQUID);
	}

	public boolean isPushable() {
		return containsFlag(FLAG_PUSHABLE);
	}

	public static class Builder {

		private String name;
		private int flags;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder flags(int flags) {
			this.flags = flags;
			return this;
		}

		public Material create() {
			return new Material(name, flags);
		}
	}
}