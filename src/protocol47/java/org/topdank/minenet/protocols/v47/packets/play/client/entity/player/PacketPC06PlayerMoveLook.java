package org.topdank.minenet.protocols.v47.packets.play.client.entity.player;

public class PacketPC06PlayerMoveLook extends PacketPC03PlayerUpdate {

	public PacketPC06PlayerMoveLook(boolean onGround, double x, double y, double z, float yaw, float pitch) {
		super(onGround);
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;

		rot = true;
		pos = true;
	}

	@Override
	public int getId() {
		return 0x06;
	}
}