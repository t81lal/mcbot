package org.topdank.mc.bot.api.entity.living.player;

import org.topdank.mc.bot.api.BotContext;
import org.topdank.mc.bot.api.Controller;

public abstract class PlayerController extends Controller<LocalPlayer> {
	
	public PlayerController(BotContext context) {
		super(context);
	}
	
	public abstract void sprint(boolean state);
	
	public abstract void crouch(boolean state);
	
	public abstract void leaveBed();
	
	public abstract void hotbarSwitch(byte slot);
	
	public abstract void swing();
}