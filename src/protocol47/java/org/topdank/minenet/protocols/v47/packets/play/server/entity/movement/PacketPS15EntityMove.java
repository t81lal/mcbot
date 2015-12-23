package org.topdank.minenet.protocols.v47.packets.play.server.entity.movement;

public class PacketPS15EntityMove extends PacketPSBaseMove {
	
	public PacketPS15EntityMove() {
		pos = true;
	}
	
	@Override
	public int getId() {
		return 0x15;
	}
}