package org.topdank.mc.bot.api.entity.living.ageable.tameable;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class OcelotEntity extends TameableEntity {

	private byte type;

	public OcelotEntity(World world, int id) {
		super(world, id, 0.6F, 0.7F);
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		if (metadata.containsKey(18)) {
			type = (byte) metadata.get(18);
		}
	}
}