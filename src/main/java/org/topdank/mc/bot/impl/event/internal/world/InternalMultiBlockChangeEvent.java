package org.topdank.mc.bot.impl.event.internal.world;

public class InternalMultiBlockChangeEvent {

	private int[] xs;
	private int[] ys;
	private int[] zs;
	private int[] data;

	public InternalMultiBlockChangeEvent(int[] xs, int[] ys, int[] zs, int[] data) {
		this.xs = xs;
		this.ys = ys;
		this.zs = zs;
		this.data = data;
	}

	public int[] getXs() {
		return xs;
	}

	public int[] getYs() {
		return ys;
	}

	public int[] getZs() {
		return zs;
	}

	public int[] getDatas() {
		return data;
	}
}
