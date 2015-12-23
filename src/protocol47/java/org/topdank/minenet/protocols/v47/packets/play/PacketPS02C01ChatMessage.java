package org.topdank.minenet.protocols.v47.packets.play;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.io.WriteableOutput;
import org.topdank.bot.net.packet.IdentifiableReadableWriteablePacket;

public class PacketPS02C01ChatMessage implements IdentifiableReadableWriteablePacket {

	private int id;
	private String msg;
	private int type;

	public PacketPS02C01ChatMessage() {
		id = 0x02;
	}

	public PacketPS02C01ChatMessage(String msg) {
		id = 0x01;
		this.msg = msg;
		System.out.println("sending " + msg);
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		msg = in.readString();
		type = in.readByte();
	}

	@Override
	public void write(WriteableOutput out) throws IOException {
		out.writeString(msg);
	}

	public String getMessage() {
		return msg;
	}

	public int getType() {
		return type;
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