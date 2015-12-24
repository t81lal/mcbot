package org.topdank.mc.bot.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.topdank.bot.eventbus.EventPriority;
import org.topdank.bot.eventbus.EventTarget;
import org.topdank.mc.bot.api.BotContext;
import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.event.internal.world.InternalBlockChangeEvent;
import org.topdank.mc.bot.api.event.internal.world.InternalChunkLoadEvent;
import org.topdank.mc.bot.api.event.internal.world.InternalMultiBlockChangeEvent;
import org.topdank.mc.bot.api.event.internal.world.InternalMultiChunkLoadEvent;
import org.topdank.mc.bot.api.event.world.TimeUpdateEvent;
import org.topdank.mc.bot.api.world.Block;
import org.topdank.mc.bot.api.world.BlockData;
import org.topdank.mc.bot.api.world.BlockId;
import org.topdank.mc.bot.api.world.WatchableObject;
import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.api.world.chunk.Chunk;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.mc.bot.api.world.pos.BoundingBox;
import org.topdank.mc.bot.api.world.pos.ChunkLocation;
import org.topdank.mc.bot.api.world.settings.WorldSettings;
import org.topdank.mc.bot.impl.entity.living.player.LocalPlayer;
import org.topdank.mc.bot.impl.entity.living.player.PlayerEntity;
import org.topdank.mc.bot.impl.entity.tile.TileEntity;

public class DefaultMinecraftWorld implements World {

	private BotContext context;
	private long age;
	private long time;
	private WorldSettings settings;
	private Map<ChunkLocation, Chunk> chunks;
	private Map<Integer, Entity> entities;
	private Map<String, PlayerEntity> players;
	private LocalPlayer localPlayer;

	public DefaultMinecraftWorld(BotContext context, WorldSettings settings) {
		this.context = context;
		this.settings = settings;
		// settings = new WorldSettings();
		entities = new HashMap<Integer, Entity>();
		players = new HashMap<String, PlayerEntity>();
	}
	
	@Override
	public long getWorldAge() {
		return age;
	}

	@Override
	public long getTime() {
		return time;
	}

	@Override
	public BlockData lookupBlockData(BlockId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBlockData(BlockLocation loc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBlockData(int x, int y, int z) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Block getBlock(BlockLocation loc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getBlock(int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Block> getCollidingBlocks(BoundingBox bb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInBlock(BoundingBox bb, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBlockData(int id, BlockLocation loc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBlockData(int id, int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TileEntity getTileEntityAt(BlockLocation loc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTileEntityAt(TileEntity tileEntity, BlockLocation loc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Chunk getChunkAt(BlockLocation loc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chunk getChunkAt(ChunkLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WorldSettings getSettings() {
		return settings;
	}

	@Override
	public Collection<Entity> getEntities() {
		synchronized (entities) {
			return entities.values();
		}
	}

	@Override
	public Entity getEntityById(int id) {
		synchronized (entities) {
			return entities.get(id);
		}
	}

	@Override
	public void spawnEntity(Entity entity) {
		if(entity != null) {
			int id = entity.getId();
			synchronized (entities) {
				if(entities.containsKey(id)) {
					Entity other = entities.get(id);
					System.err.printf("Entity id clash: [%d] {%s} {%s}.%n", id, entity, other);
				}
				entities.put(id, entity);
			}
			
			if(entity instanceof PlayerEntity) {
				spawnPlayer((PlayerEntity) entity);
			}
		} else {
			System.err.println("Can't spawn null entity.");
		}
	}
	
	private void spawnPlayer(PlayerEntity p) {
		if(p != null) {
			String name = p.getName();
			if(name != null) {
				synchronized (players) {
					if(players.containsKey(name)) {
						PlayerEntity other = players.get(name);
						System.err.printf("Player name clash: [%s] {%s} {%s}.%n", name, p, other);
					}
					players.put(name, p);
					if(p instanceof LocalPlayer) {
						setLocalPlayer((LocalPlayer) p);
					}
				}
			} else {
				System.err.printf("Player [%d/spawn] has null name?%n", p.getId());
			}
		} else {
			System.err.println("Can't spawn null player.");
		}
	}

	private void setLocalPlayer(LocalPlayer player) {
		if(player != null) {
			localPlayer = player;
		} else {
			System.err.println("Can't set null local player.%n");
		}
	}
	
	@Override
	public Entity despawnEntity(int id) {
		synchronized (entities) {
			if(entities.containsKey(id)) {
				Entity e = entities.remove(id);
				if(e != null) {
					if(e instanceof PlayerEntity) {
						despawnPlayer((PlayerEntity) e);
					}
					return e;
				} else {
					System.err.printf("Despawned player [%d] was null?%n", id);
				}
			} else {
				System.err.printf("Tried to despawn entity that didn't exist [%d].%n", id);
			}
		}
		return null;
	}

	private void despawnPlayer(PlayerEntity p) {
		if(p != null) {
			String name = p.getName();
			if(name != null) {
				synchronized (players) {
					players.remove(name);
				}
				if(p instanceof LocalPlayer) {
					localPlayer = (LocalPlayer) p;
				}
			} else {
				System.err.printf("Player [%d/despawn] has null name?%n", p.getId());
			}
		} else {
			System.err.println("Can't despawn null player!");
		}
	}
	
	@Override
	public void despawnEntity(Entity entity) {
		if(entity != null) {
			despawnEntity(entity.getId());
		} else {
			System.err.println("Can't despawn null entity!");
		}
	}

	@Override
	public PlayerEntity getPlayerByName(String name) {
		if(name == null) {
			System.err.println("Players can't have null names!");
		} else {
			synchronized (players) {
				if(players.containsKey(name)) {
					return players.get(name);
				} else {
					System.err.printf("No player goes by the name '%s'.%n", name);
				}
			}
		}
		
		return null;
	}

	@Override
	public Collection<PlayerEntity> getPlayers() {
		synchronized (players) {
			return players.values();
		}
	}

	@Override
	public LocalPlayer getLocalPlayer() {
		return localPlayer;
	}

	@Override
	public void watch(WatchableObject o) {
		throw new UnsupportedOperationException("DefaultMinecraftWorlds don't support tickable objects.");
	}

	@Override
	public void unwatch(WatchableObject o) {
		throw new UnsupportedOperationException("DefaultMinecraftWorlds don't support tickable objects.");
	}
	
	@EventTarget(priority = EventPriority.HIGHEST)
	private void onTimeUpdateEvent(TimeUpdateEvent e) {
		time = e.getTime();
		age = e.getAge();
	}
	
	@EventTarget(priority = EventPriority.HIGHEST)
	private void onInternalChunkLoad(InternalChunkLoadEvent e) {
		synchronized (chunks) {
			chunks.put(new ChunkLocation(e.getChunkX(), e.getChunkY(), e.getChunkZ()), e.getChunk());
		}
	}

	@EventTarget(priority = EventPriority.HIGHEST)
	private void onInternalMultiChunkLoadEvent(InternalMultiChunkLoadEvent e) {
		synchronized (chunks) {
			Chunk[] chunks = e.getChunks();
			int length = chunks.length;
			int[] xs = e.getChunkXs();
			int[] ys = e.getChunkYs();
			int[] zs = e.getChunkZs();

			for(int i = 0; i < length; i++) {
				this.chunks.put(new ChunkLocation(xs[i], ys[i], zs[i]), chunks[i]);
			}
		}
	}
	
	@EventTarget(priority = EventPriority.HIGHEST)
	private void onInternalBlockChangeEvent(InternalBlockChangeEvent e) {
		int x = e.getX(), y = e.getY(), z = e.getZ();
		int chunkX = x >> 4, chunkY = y >> 4, chunkZ = z >> 4;
		Chunk chunk = getChunkAt(new ChunkLocation(chunkX, chunkY, chunkZ));
		if (chunk == null)
			return;

		int chunkBaseX = chunkX << 4, chunkBaseY = chunkY << 4, chunkBaseZ = chunkZ << 4;
		chunk.getBlocks().set(x - chunkBaseX, y - chunkBaseY, z - chunkBaseZ, e.getData());
	}

	@EventTarget(priority = EventPriority.HIGHEST)
	private void onInternalMultiBlockChangeEvent(InternalMultiBlockChangeEvent e) {
		int[] xs = e.getXs(), ys = e.getYs(), zs = e.getZs();
		int[] datas = e.getDatas();
		int length = datas.length;
		for (int i = 0; i < length; i++) {
			int x = xs[i], y = ys[i], z = zs[i];
			int chunkX = x >> 4, chunkY = y >> 4, chunkZ = z >> 4;
			Chunk chunk = getChunkAt(new ChunkLocation(chunkX, chunkY, chunkZ));
			if (chunk == null)
				continue;
			int chunkBaseX = chunkX << 4, chunkBaseY = chunkY << 4, chunkBaseZ = chunkZ << 4;
			chunk.getBlocks().set(x - chunkBaseX, y - chunkBaseY, z - chunkBaseZ, datas[i]);
		}
	}
}