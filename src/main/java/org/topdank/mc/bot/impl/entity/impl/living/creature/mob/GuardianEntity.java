package org.topdank.mc.bot.impl.entity.impl.living.creature.mob;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class GuardianEntity extends MobEntity {

	private boolean isElder;
	private boolean isRetractingSpikes;
	private int targetId;
	
	public GuardianEntity(World world, int id) {
		super(world, id, 0.85F, 0.85F);
	}

	public boolean isElder() {
		return isElder;
	}
	
	public void setElder(boolean isElder) {
		this.isElder = isElder;
		
		if(isElder) {
			setSize(1.9975F, 1.9975F);
		}
	}

	public boolean isRetractingSpikes() {
		return isRetractingSpikes;
	}

	public void setRetractingSpikes(boolean isRetractingSpikes) {
		this.isRetractingSpikes = isRetractingSpikes;
	}

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if(metadata.containsKey(16)) {
			int flag = (int) metadata.get(16);
			setRetractingSpikes((flag & 2) != 0);
			setElder((flag & 4) != 0);
		}
		if(metadata.containsKey(17)) {
			setTargetId((int) metadata.get(17));
		}
	}
}