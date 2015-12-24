package org.topdank.mc.bot.impl.entity.minecart;

import java.util.Map;

import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.world.World;

public class MinecartEntity extends Entity {
	
	protected float damageTaken;
	// synonymous to rollingAmplitude
	protected int shakingPower;
	// synonymous to rollingDirection
	protected int shakeDirection;
	protected int carriedBlockId;
	protected int carriedBlockData;
	protected int carriedBlockYPos;
	protected boolean showCarriedBlock;
	protected MinecartType type;

	public MinecartEntity(World world, int id, MinecartType type) {
		super(world, id, 0.98F, 0.7F);
		this.type = type;
	}

	public float getDamageTaken() {
		return damageTaken;
	}

	public void setDamageTaken(float damageTaken) {
		this.damageTaken = damageTaken;
		
		if(isBroken()) {
			setDead(true);
		}
	}
	
	public boolean isBroken() {
		return damageTaken > 40;
	}
	
	public MinecartType getType() {
		return type;
	}

	public void setType(MinecartType type) {
		this.type = type;
	}

	public int getShakingPower() {
		return shakingPower;
	}

	public void setShakingPower(int shakingPower) {
		this.shakingPower = shakingPower;
	}

	public int getShakeDirection() {
		return shakeDirection;
	}

	public void setShakeDirection(int shakeDirection) {
		this.shakeDirection = shakeDirection;
	}

	public int getCarriedBlockId() {
		return carriedBlockId;
	}

	public void setCarriedBlockId(int carriedBlockId) {
		this.carriedBlockId = carriedBlockId;
	}

	public int getCarriedBlockData() {
		return carriedBlockData;
	}

	public void setCarriedBlockData(int carriedBlockData) {
		this.carriedBlockData = carriedBlockData;
	}

	public int getCarriedBlockYPos() {
		return carriedBlockYPos;
	}

	public void setCarriedBlockYPos(int carriedBlockYPos) {
		this.carriedBlockYPos = carriedBlockYPos;
	}

	public boolean isShowCarriedBlock() {
		return showCarriedBlock;
	}

	public void setShowCarriedBlock(boolean showCarriedBlock) {
		this.showCarriedBlock = showCarriedBlock;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(17)) {
			setShakingPower((int) metadata.get(17));
		}

		if (metadata.containsKey(18)) {
			setShakeDirection((int) metadata.get(18));
		}

		if (metadata.containsKey(19)) {
			setDamageTaken((float) metadata.get(19));
		}

		if (metadata.containsKey(20)) {
			int flags = (int) metadata.get(20);
			// setCarriedBlockId(flags & 0x00FF);
			// setCarriedBlockId(flags & 0xFF00);
	        // int i = id & 4095;
	        // int j = id >> 12 & 15;
			setCarriedBlockId(flags & 4095);
			setCarriedBlockData((flags >> 12) & 15);
		}

		if (metadata.containsKey(21)) {
			setCarriedBlockYPos((int) metadata.get(21));
		}

		if (metadata.containsKey(22)) {
			setShowCarriedBlock((byte) metadata.get(22) == 1);
		}
	}
	
	public static enum MinecartType {
		RIDEABLE(0, "MinecartRideable"), 
		CHEST(1, "MinecartChest"), 
		FURNACE(2, "MinecartFurnace"), 
		TNT(3, "MinecartTNT"), 
		SPAWNER(4, "MinecartSpawner"), 
		HOPPER(5, "MinecartHopper"), 
		COMMAND_BLOCK(6, "MinecartCommandBlock");

		private final int networkID;
		private final String name;

		private MinecartType(int networkID, String name) {
			this.networkID = networkID;
			this.name = name;
		}

		public int getNetworkID() {
			return this.networkID;
		}

		public String getName() {
			return this.name;
		}

		public static MinecartType byNetworkID(int id) {
			for(MinecartType type : values()) {
				if(type.networkID == id) {
					return type;
				}
			}
			return RIDEABLE;
		}
	}
}