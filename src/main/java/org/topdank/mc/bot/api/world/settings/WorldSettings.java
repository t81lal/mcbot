package org.topdank.mc.bot.api.world.settings;

import org.topdank.mc.bot.api.world.pos.BlockLocation;

public class WorldSettings {
	
	private int height;
	private Difficulty difficulty;
	private boolean hardcore;
	private Dimension dimension;
	private GameMode gameMode;
	private WorldType worldType;
	private int maxPlayers;
	private BlockLocation spawnLocation;
	
	public WorldSettings() {
	}
	
	public WorldSettings(int height, Difficulty difficulty, boolean hardcore, Dimension dimension, GameMode gameMode, WorldType worldType, int maxPlayers, BlockLocation spawnLocation) {
		this.height = height;
		this.difficulty = difficulty;
		this.hardcore = hardcore;
		this.dimension = dimension;
		this.gameMode = gameMode;
		this.worldType = worldType;
		this.maxPlayers = maxPlayers;
		this.spawnLocation = spawnLocation;
	}
	
	public int getHeight() {
		return height;
	}
	
	public WorldSettings setHeight(int height) {
		this.height = height;
		return this;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	public WorldSettings setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
		return this;
	}
	
	public boolean isHardcore() {
		return hardcore;
	}
	
	public WorldSettings setHardcore(boolean hardcore) {
		this.hardcore = hardcore;
		return this;
	}
	
	public Dimension getDimension() {
		return dimension;
	}
	
	public WorldSettings setDimension(Dimension dimension) {
		this.dimension = dimension;
		return this;
	}
	
	public GameMode getGameMode() {
		return gameMode;
	}
	
	public WorldSettings setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
		return this;
	}
	
	public WorldType getWorldType() {
		return worldType;
	}
	
	public WorldSettings setWorldType(WorldType worldType) {
		this.worldType = worldType;
		return this;
	}
	
	public int getMaxPlayers() {
		return maxPlayers;
	}
	
	public WorldSettings setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
		return this;
	}
	
	public BlockLocation getSpawnLocation() {
		return spawnLocation;
	}
	
	public WorldSettings setSpawnLocation(BlockLocation spawnLocation) {
		this.spawnLocation = spawnLocation;
		return this;
	}
}