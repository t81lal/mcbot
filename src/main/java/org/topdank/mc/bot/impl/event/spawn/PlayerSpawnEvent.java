package org.topdank.mc.bot.impl.event.spawn;

import org.topdank.bot.eventbus.Event;
import org.topdank.mc.bot.impl.entity.living.player.PlayerEntity;

public class PlayerSpawnEvent implements Event {

	private PlayerEntity player;

	public PlayerSpawnEvent(PlayerEntity player) {
		this.player = player;
	}

	public PlayerEntity getPlayer() {
		return player;
	}
}