package org.topdank.mc.bot.impl.entity.living.creature.ageable;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class VillagerEntity extends AgeableEntity {

	private VillagerProfession profession;

	public VillagerEntity(World world, int id) {
		super(world, id, 0.6F, 1.8F);
	}

	public VillagerProfession getProfession() {
		return profession;
	}

	public void setProfession(VillagerProfession profession) {
		this.profession = profession;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			setProfession(VillagerProfession.values()[(int) metadata.get(16)]);
		}
	}

	public enum VillagerProfession {
		FARMER,
		LIBRARIAN,
		PRIEST,
		BLACKSMITH,
		BUTCHER;
	}
}