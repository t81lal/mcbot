package org.topdank.mc.bot.impl.entity.living.creature.mob;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class SkeletonEntity extends MobEntity {

	private SkeletonType type = SkeletonType.NORMAL;

	public SkeletonEntity(World world, int id) {
		super(world, id, 0.6F, 1.95F);
	}

	public SkeletonType getType() {
		return type;
	}

	public void setType(SkeletonType type) {
		this.type = type;
		
		if(type == SkeletonType.WITHER) {
			setSize(0.72F, 2.535F);
		} else {
			setSize(0.6F, 1.95F);
		}
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(13)) {
			byte val = (byte) metadata.get(13);
			if(val == 1) {
				setType(SkeletonType.WITHER);
			} else {
				System.out.println("Skeleton type: " + val);
				setType(SkeletonType.NORMAL);
			}
		}
	}

	public enum SkeletonType {
		NORMAL,
		WITHER;
	}
}