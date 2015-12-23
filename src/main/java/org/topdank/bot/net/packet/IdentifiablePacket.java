package org.topdank.bot.net.packet;

public interface IdentifiablePacket extends Packet {

	@Override
	boolean isPriorityPacket();

	int getId();

	@Override
	boolean isIdentifiable();
}