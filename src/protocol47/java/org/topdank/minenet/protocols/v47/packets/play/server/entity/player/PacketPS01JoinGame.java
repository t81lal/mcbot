package org.topdank.minenet.protocols.v47.packets.play.server.entity.player;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.world.settings.Difficulty;
import org.topdank.mc.bot.api.world.settings.Dimension;
import org.topdank.mc.bot.api.world.settings.GameMode;
import org.topdank.mc.bot.api.world.settings.WorldType;

public class PacketPS01JoinGame implements IdentifiableReadablePacket {

	private int entityId;
	private boolean hardcore;
	private GameMode gameMode;
	private Dimension dimension;
	private Difficulty difficulty;
	private int maxPlayers;
	private WorldType worldType;
	private boolean rdi; // reduced debug info

	@Override
	public void read(ReadableInput in) throws IOException {
		entityId = in.readInt();
		int gm = in.readUnsignedByte();
		hardcore = (gm & 8) == 8;
		gameMode = GameMode.getGameModeById(gm & -9);
		dimension = Dimension.getDimensionById(in.readByte());
		difficulty = Difficulty.getDifficultyById(in.readUnsignedByte());
		maxPlayers = in.readUnsignedByte();
		worldType = WorldType.parseWorldType(in.readString().toLowerCase());
		rdi = in.readBoolean();
	}

	public int getEntityId() {
		return entityId;
	}

	public boolean isHardcore() {
		return hardcore;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public WorldType getWorldType() {
		return worldType;
	}

	public boolean isRdi() {
		return rdi;
	}

	@Override
	public boolean isPriorityPacket() {
		return true;
	}

	@Override
	public int getId() {
		return 0x01;
	}
}