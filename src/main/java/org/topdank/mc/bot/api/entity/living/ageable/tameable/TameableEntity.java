package org.topdank.mc.bot.api.entity.living.ageable.tameable;

import java.util.Map;

import org.topdank.mc.bot.api.entity.living.ageable.AgeableEntity;
import org.topdank.mc.bot.api.world.World;

public class TameableEntity extends AgeableEntity {

	private boolean isSitting;
	private boolean isTame;
	private String owner;

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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
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
			setOwner((String) metadata.get(17));
		}
	}
}