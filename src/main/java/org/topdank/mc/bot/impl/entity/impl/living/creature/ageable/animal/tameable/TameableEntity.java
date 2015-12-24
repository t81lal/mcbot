package org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal.tameable;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.AgeableEntity;

public class TameableEntity extends AgeableEntity {

	private boolean isSitting;
	private boolean isTame;
	private String ownerUUID;

	public TameableEntity(World world, int id, float width, float height) {
		super(world, id, width, height);
	}

	public boolean isSitting() {
		return isSitting;
	}

	public void setSitting(boolean isSitting) {
		this.isSitting = isSitting;
	}

	public boolean isTame() {
		return isTame;
	}

	public void setTame(boolean isTame) {
		this.isTame = isTame;
	}

	public String getOwnerUUID() {
		return ownerUUID;
	}

	public void setOwnerUUID(String owner) {
		this.ownerUUID = owner;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			byte flags = (byte) metadata.get(16);
			setSitting((flags & 0x01) != 0);
			setTame((flags & 0x04) != 0);
		}

		if (metadata.containsKey(17)) {
			setOwnerUUID((String) metadata.get(17));
		}
	}
	
	@Override
	public void updateStatus(int id) {
		if(id == 6) {
			// spawn smoke particles
		} else if(id == 7) {
			// spawn heart particles
		} else {
			super.updateStatus(id);
		}
	}
}