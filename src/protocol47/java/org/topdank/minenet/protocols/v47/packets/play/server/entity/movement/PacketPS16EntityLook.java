package org.topdank.minenet.protocols.v47.packets.play.server.entity.movement;

public class PacketPS16EntityLook extends PacketPSBaseMove {
	
	public PacketPS16EntityLook() {
		rot = true;
	}
	
	@Override
	public int getId() {
		return 0x16;
	}
}