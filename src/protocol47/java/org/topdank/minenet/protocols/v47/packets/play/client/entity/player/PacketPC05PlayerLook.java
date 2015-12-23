package org.topdank.minenet.protocols.v47.packets.play.client.entity.player;

public class PacketPC05PlayerLook extends PacketPC03PlayerUpdate {

	public PacketPC05PlayerLook(boolean onGround, float yaw, float pitch) {
		super(onGround);

		rot = true;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	@Override
	public int getId() {
		return 0x05;
	}
}