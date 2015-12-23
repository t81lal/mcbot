package org.topdank.minenet.protocols.v47.packets.play.server.entity.movement;

public class PacketPS17EntityLookMove extends PacketPSBaseMove {
	
	public PacketPS17EntityLookMove() {
		pos = true;
		rot = true;
	}
	
	@Override
	public int getId() {
		return 0x17;
	}
}