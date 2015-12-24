package org.topdank.mc.bot.api.world;

import java.util.Collection;
import java.util.Set;

import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.world.chunk.Chunk;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.mc.bot.api.world.pos.BoundingBox;
import org.topdank.mc.bot.api.world.pos.ChunkLocation;
import org.topdank.mc.bot.api.world.settings.WorldSettings;
import org.topdank.mc.bot.impl.entity.living.player.LocalPlayer;
import org.topdank.mc.bot.impl.entity.living.player.PlayerEntity;
import org.topdank.mc.bot.impl.entity.tile.TileEntity;

public interface World {

	long getWorldAge();

	long getTime();

	BlockData lookupBlockData(BlockId id);
	
	int getBlockData(BlockLocation loc);

	int getBlockData(int x, int y, int z);

	Block getBlock(BlockLocation loc);

	Block getBlock(int x, int y, int z);

	Set<Block> getCollidingBlocks(BoundingBox bb);
	
	boolean isInBlock(BoundingBox bb, String name);
	
	void setBlockData(int id, BlockLocation loc);

	void setBlockData(int id, int x, int y, int z);

	TileEntity getTileEntityAt(BlockLocation loc);

	void setTileEntityAt(TileEntity tileEntity, BlockLocation loc);

	Chunk getChunkAt(BlockLocation loc);

	Chunk getChunkAt(ChunkLocation location);

	void destroy();

	WorldSettings getSettings();

	////////////////////////////////////////
	
	Collection<Entity> getEntities();

	Entity getEntityById(int id);

	void spawnEntity(Entity entity);

	Entity despawnEntity(int id);

	void despawnEntity(Entity entity);

	PlayerEntity getPlayerByName(String name);

	Collection<PlayerEntity> getPlayers();

	LocalPlayer getLocalPlayer();

	void watch(WatchableObject o);

	void unwatch(WatchableObject o);
}