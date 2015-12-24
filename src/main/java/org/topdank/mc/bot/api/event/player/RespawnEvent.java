package org.topdank.mc.bot.api.event.player;

import java.awt.Dimension;

import org.topdank.bot.eventbus.Event;
import org.topdank.mc.bot.api.world.settings.Difficulty;
import org.topdank.mc.bot.api.world.settings.GameMode;
import org.topdank.mc.bot.api.world.settings.WorldType;

public class RespawnEvent implements Event {

	private final Dimension dimension;
	private final Difficulty difficulty;
	private final GameMode gameMode;
	private final WorldType worldType;

	public RespawnEvent(Dimension dimension, Difficulty difficulty, GameMode gameMode, WorldType worldType) {
		this.dimension = dimension;
		this.difficulty = difficulty;
		this.gameMode = gameMode;
		this.worldType = worldType;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public WorldType getWorldType() {
		return worldType;
	}
}