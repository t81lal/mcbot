package org.topdank.bot.net.packet;

public interface Packet {

	boolean isPriorityPacket();

	boolean isIdentifiable();
}