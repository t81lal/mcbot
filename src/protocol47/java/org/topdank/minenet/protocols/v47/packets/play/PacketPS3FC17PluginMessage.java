package org.topdank.minenet.protocols.v47.packets.play;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableReadableWriteablePacket;

public class PacketPS3FC17PluginMessage implements IdentifiableReadableWriteablePacket {

	private int id;
	private String channel;
	private byte[] data;

	public PacketPS3FC17PluginMessage() {
		id = 0x3F;
	}

	public PacketPS3FC17PluginMessage(String channel, byte[] data) {
		id = 0x17;
		this.channel = channel;
		this.data = data;
	}

	public String getChannel() {
		return channel;
	}

	public byte[] getData() {
		return data;
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeString(channel);
		out.writeBytes(data);
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		channel = in.readString();
		int len = in.written();
		data = in.readBytes(len);
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return id;
	}
}