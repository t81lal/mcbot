package org.topdank.mc.bot.impl.entity.impl.minecart;

import java.util.Map;

import org.topdank.mc.bot.api.world.World;

public class CommandBlockMinecart extends MinecartEntity {

	private String command;
	private String lastOutput;
	
	public CommandBlockMinecart(World world, int id) {
		super(world, id, MinecartType.COMMAND_BLOCK);
	}
	
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getLastOutput() {
		return lastOutput;
	}

	public void setLastOutput(String lastOutput) {
		this.lastOutput = lastOutput;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);
		
		System.out.println("Minecart metadata: " + metadata);
		
		if(metadata.containsKey(23)) {
			setCommand((String) metadata.get(23));
		}
		if(metadata.containsKey(24)) {
			setLastOutput((String) metadata.get(23));
		}
	}
}