package org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal.tameable;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class OcelotEntity extends TameableEntity {

	private CatType type;

	public OcelotEntity(World world, int id) {
		super(world, id, 0.6F, 0.7F);
	}

	public CatType getType() {
		return type;
	}
	
	public void setType(CatType type) {
		this.type = type;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		if (metadata.containsKey(18)) {
			setType(CatType.values()[(byte)metadata.get(18)]);
		}
	}
	
	public enum CatType {
		DEFUALT,
		BLACK,
		RED,
		SIAMESE
	}
}