package org.topdank.minenet.protocols.v47.packets.play.client.entity.player;

public class PacketPC04PlayerMove extends PacketPC03PlayerUpdate {

	public PacketPC04PlayerMove(boolean onGround, double x, double y, double z) {
		super(onGround);

		pos = true;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public int getId() {
		return 0x04;
	}
}