package org.topdank.mc.bot.api.world.chunk;

public class NibbleArray3d {
	
	private byte[] data;
	
	public NibbleArray3d(int size) {
		data = new byte[size >> 1];
	}
	
	public NibbleArray3d(byte[] array) {
		data = array;
	}
	
	public byte[] getData() {
		return data;
	}
	
	public int get(int x, int y, int z) {
		int key = (y << 8) | (z << 4) | x;
		int index = key >> 1;
		int part = key & 1;
		return part == 0 ? data[index] & 15 : (data[index] >> 4) & 15;
	}
	
	public void set(int x, int y, int z, int val) {
		int key = (y << 8) | (z << 4) | x;
		int index = key >> 1;
		int part = key & 1;
		if (part == 0) {
			data[index] = (byte) ((data[index] & 240) | (val & 15));
		} else {
			data[index] = (byte) ((data[index] & 15) | ((val & 15) << 4));
		}
	}
	
	public void fill(int val) {
		for (int index = 0; index < (data.length << 1); index++) {
			int ind = index >> 1;
			int part = index & 1;
			if (part == 0) {
				data[ind] = (byte) ((data[ind] & 240) | (val & 15));
			} else {
				data[ind] = (byte) ((data[ind] & 15) | ((val & 15) << 4));
			}
		}
	}
}