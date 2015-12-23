package org.topdank.minenet.protocols.v47.packets.play.server.sound;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS29SoundEffect implements IdentifiableReadablePacket {

	private String sound;
	private double x;
	private double y;
	private double z;
	private float volume;
	private float pitch;

	public PacketPS29SoundEffect() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		sound = in.readString();
		x = in.readInt() / 8D;
		y = in.readInt() / 8D;
		z = in.readInt() / 8D;
		volume = in.readFloat();
		pitch = in.readUnsignedByte() / 63f;
	}

	public String getSound() {
		return sound;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public float getVolume() {
		return volume;
	}

	public float getPitch() {
		return pitch;
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x29;
	}

	@Override
	public String toString() {
		return "PacketPS29SoundEffect [sound=" + sound + ", x=" + x + ", y=" + y + ", z=" + z + ", volume=" + volume + ", pitch=" + pitch + "]";
	}
}