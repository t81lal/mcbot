package org.topdank.mc.bot.api.entity.living.player;

import java.util.Map;

import org.topdank.mc.bot.api.entity.living.LivingEntity;
import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.mc.bot.api.world.settings.GameMode;

public class PlayerEntity extends LivingEntity {

	protected String name;
	protected boolean capeHiden;
	protected float absorptionHearts;
	protected int score;
	protected GameMode gameMode;
	protected BlockLocation bedLocation;

	public PlayerEntity(World world, int id, String name, GameMode gameMode) {
		super(world, id, 0.6F, 1.8F);
		this.name = name;
		this.gameMode = gameMode;
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

	// Index Type Meaning
	// 10 Unsigned Byte Skin flags
	// 16 Byte Bit Mask 0x02 Hide Cape
	// 17 Float Absorption Hearts
	// 18 Int Score

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(16)) {
			byte flags = (byte) metadata.get(16);
			setCapeHiden((flags & 0x02) == 1);
		}

		if (metadata.containsKey(17))
			setAbsorptionHearts((float) metadata.get(17));

		if (metadata.containsKey(18))
			setScore((int) metadata.get(18));
	}

	@Override
	public void update() {
		super.update();
	}
}