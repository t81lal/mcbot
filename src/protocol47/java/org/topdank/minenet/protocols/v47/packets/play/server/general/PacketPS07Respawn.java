package org.topdank.minenet.protocols.v47.packets.play.server.general;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.mc.bot.api.world.settings.Difficulty;
import org.topdank.mc.bot.api.world.settings.Dimension;
import org.topdank.mc.bot.api.world.settings.GameMode;
import org.topdank.mc.bot.api.world.settings.WorldType;

public class PacketPS07Respawn implements IdentifiableReadablePacket {

	private Dimension dimension;
	private Difficulty difficulty;
	private GameMode gamemode;
	private WorldType worldType;

	public PacketPS07Respawn() {
	}

	public Dimension getDimension() {
		return dimension;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public GameMode getGameMode() {
		return gamemode;
	}

	public WorldType getWorldType() {
		return worldType;
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		dimension = Dimension.getDimensionById(in.readInt());
		difficulty = Difficulty.getDifficultyById(in.readUnsignedByte());
		gamemode = GameMode.getGameModeById(in.readUnsignedByte());
		worldType = WorldType.parseWorldType(in.readString().toLowerCase());
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x07;
	}
}