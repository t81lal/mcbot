package org.topdank.mc.bot.impl.entity.impl.living.player;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.mc.bot.api.world.settings.GameMode;
import org.topdank.mc.bot.impl.entity.impl.living.LivingEntity;

public class PlayerEntity extends LivingEntity {

	protected String name;
	protected boolean capeHiden;
	protected float absorptionHearts;
	protected int score;
	protected GameMode gameMode;
	protected BlockLocation bedLocation;
	protected boolean[] wearing;

	public PlayerEntity(World world, int id, String name, GameMode gameMode) {
		super(world, id, 0.6F, 1.8F);
		this.name = name;
		this.gameMode = gameMode;
		
		wearing = new boolean[PlayerModelPart.values().length];
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCapeHiden() {
		return capeHiden;
	}

	public void setCapeHiden(boolean capeHiden) {
		this.capeHiden = capeHiden;
	}

	public float getAbsorptionHearts() {
		return absorptionHearts;
	}

	public void setAbsorptionHearts(float absorptionHearts) {
		this.absorptionHearts = absorptionHearts;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}

	public BlockLocation getBedLocation() {
		return bedLocation;
	}

	public void setBedLocation(BlockLocation bedLocation) {
		this.bedLocation = bedLocation;
	}
	
	public boolean isWearing(PlayerModelPart part) {
		if(part == null) {
			return false;
		} else {
			int id = part.getId();
			if(id < 0 || id >= PlayerModelPart.values().length) {
				return false;
			} else {
				return wearing[id];
			}
		}
	}

	// Index Type Meaning
	// 10 Unsigned Byte Skin flags
	// 16 Byte Bit Mask 0x02 Hide Cape
	// 17 Float Absorption Hearts
	// 18 Int Score

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if(metadata.containsKey(10)) {
			byte flag = (byte) metadata.get(10);
			
			for(int i=0; i < wearing.length; i++) {
				PlayerModelPart part = PlayerModelPart.values()[i];
				wearing[i] = (flag & part.getMask()) == part.getMask();
			}
		}
		
		if (metadata.containsKey(16)) {
			System.out.printf("Got [%s] metadata: %s.%n", getName(), metadata);
			byte flags = (byte) metadata.get(16);
			setCapeHiden((flags & 0x02) == 1);
		}

		if (metadata.containsKey(17)) {
			setAbsorptionHearts((float) metadata.get(17));
		}

		if (metadata.containsKey(18)) {
			setScore((int) metadata.get(18));
		}
	}

	@Override
	public void update() {
		super.update();
	}
	
	public enum PlayerModelPart {
	    CAPE(0, "cape"),
	    JACKET(1, "jacket"),
	    LEFT_SLEEVE(2, "left_sleeve"),
	    RIGHT_SLEEVE(3, "right_sleeve"),
	    LEFT_PANTS_LEG(4, "left_pants_leg"),
	    RIGHT_PANTS_LEG(5, "right_pants_leg"),
	    HAT(6, "hat");
		
		private final int id;
		private final int mask;
		private final String name;
		
		PlayerModelPart(int i, String name) {
			id = i;
			mask = (1 << i);
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public int getMask() {
			return mask;
		}

		public String getName() {
			return name;
		}
	}
}