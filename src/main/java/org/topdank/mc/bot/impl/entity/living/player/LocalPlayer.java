package org.topdank.mc.bot.impl.entity.living.player;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.topdank.bot.eventbus.EventBus;
import org.topdank.mc.bot.api.BotContext;
import org.topdank.mc.bot.api.entity.Entity;
import org.topdank.mc.bot.api.item.ItemStack;
import org.topdank.mc.bot.api.item.inventory.Inventory;
import org.topdank.mc.bot.api.item.inventory.PlayerInventory;
import org.topdank.mc.bot.api.item.tool.ToolType;
import org.topdank.mc.bot.api.world.Block;
import org.topdank.mc.bot.api.world.BlockData;
import org.topdank.mc.bot.api.world.BlockId;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.mc.bot.api.world.pos.BoundingBox;
import org.topdank.mc.bot.api.world.pos.PreciseLocation;
import org.topdank.mc.bot.impl.event.internal.player.RequestRespawnEvent;
import org.topdank.mc.bot.util.MathHelper;

public class LocalPlayer extends PlayerEntity {

	protected BotContext context;
	protected EventBus bus;

	private PlayerController controller;

	private PlayerInventory inventory;
	private Inventory window;

	private int hunger;
	private float foodSaturation;

	private int experienceLevel, experienceTotal;
	private double lastX, lastY, lastZ, lastYaw, lastPitch;

	private boolean activatedJump;

	public LocalPlayer(BotContext context, PlayerController controller, int id, String name) {
		super(context.getWorld(), id, name, context.getWorld().getSettings().getGameMode());
		this.context = context;
		this.controller = controller;
		bus = context.getEventBus();
		inventory = new PlayerInventory(context, this);
	}

	private double highY;

	public void jump() {
		if (activatedJump || !isOnGround())
			return;
		motY = 0.42;
		activatedJump = true;
		super.update();
	}

	@Override
	public void update() {
		updateGravity();
		checkCollisions();

		super.update();

		if (y > highY) {
			highY = y;
			System.out.println("High Y: " + highY);
		}
	}

	public void updatePosition() {
		boolean move = (x != lastX) || (y != lastY) || (z != lastZ);
		boolean rotate = (yaw != lastYaw) || (pitch != lastPitch);
		updatePosition(move, rotate);
	}

	public void updatePosition(boolean move, boolean rotate) {
		if (move && rotate) {
			lastX = x;
			lastY = y;
			lastZ = z;
			lastPitch = pitch;
			lastYaw = yaw;
		} else if (move) {
			lastX = x;
			lastY = y;
			lastZ = z;
		} else if (rotate) {
			lastPitch = pitch;
			lastYaw = yaw;
		}
	}

	private void updateGravity() {
		double distanceToGround = distanceToGround();
		// double horizFactor = 0.91;
		if (!activatedJump && (distanceToGround <= 0.08)) {
			// BlockData data = world.getBlockRegistry().getByKey(BlockId.create(world.getBlockData((int) Math.floor(x), (int)
			// Math.floor(y
			// - 0.1), (int) Math.floor(z))));
			// if (data.isSolid())
			// horizFactor *= data.getFriction();
			// System.out.println(String.format("Standing on %s solid:%b friction:%.3f", data.getName(), data.isSolid(),
			// data.getFriction()));
			// System.out.println("onground at y " + y + " moty " + motY);

			// if (world.getBlockData((int) x, (int) y - 1, (int) z) != -1) {
			// System.out.println("Block under = " + world.getBlockRegistry().getByKey(BlockId.create(world.getBlockData((int) x, (int) y -
			// 1, (int) z))).getName() + " at "
			// + new BlockLocation((int) x, (int) y - 1, (int) z));
			// }

			motY = 0;
		} else {
			if (motY >= -3.92)
				motY -= 0.08D;
			if (!activatedJump && (Math.abs(motY) > distanceToGround))
				motY = -distanceToGround;
			// motY = motY - (motY * 0.02);// drag

			// if (activatedJump) {
			// System.out.println(String.format("Jumping after motY:%.3f", motY));
			// }
			activatedJump = false;
		}

		// System.out.println(String.format("Jumping x:%.3f, y:%.3f, z:%.3f, motX:%.3f, motY:%.3f, motZ:%.3f, pitch:%.3f, yaw:%.3f", x, y,
		// z, motX, motY, motZ, pitch, yaw));
	}

	private void checkCollisions() {
		if (colliding(x, y, z) && !colliding(x, y + 1, z))
			// y = (int) Math.ceil(y) + 1;
			jump();

	}

	//	private boolean colliding(BoundingBox bb) {
	//		return false;
	//	}

	private boolean colliding(double x, double y, double z) {
		BlockData dataAt = world.lookupBlockData(BlockId.create(world.getBlockData((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z))));
		if ((dataAt == null) || !dataAt.isSolid())
			return false;
		return true;
	}

	public void handleCollision() {
		BoundingBox bounds = getBoundingBox();
		Set<Block> currentCollisions = world.getCollidingBlocks(bounds);
		final double off = 0.01;
		double velocity = 0;
		for (double v = 0, target = Math.abs(motX), sign = Math.signum(motX); (v < (target + off)) && !collides(bounds.offset(sign * v, 0, 0), currentCollisions); v += off)
			velocity = sign * Math.min(v, target);
		motX = velocity;
		velocity = 0;
		for (double v = 0, target = Math.abs(motZ), sign = Math.signum(motZ); (v < (target + off)) && !collides(bounds.offset(0, 0, sign * v), currentCollisions); v += off)
			velocity = sign * Math.min(v, target);
		motZ = velocity;
		velocity = 0;
		for (double v = 0, target = Math.abs(motY), sign = Math.signum(motY); (v < (target + off)) && !collides(bounds.offset(0, sign * v, 0), currentCollisions); v += off)
			velocity = sign * Math.min(v, target);
		motY = velocity;
		if (collides(bounds.offset(motX, 0, 0), currentCollisions))
			motX = 0;
		if (collides(bounds.offset(0, 0, motZ), currentCollisions))
			motZ = 0;
		if (collides(bounds.offset(0, motY, 0), currentCollisions))
			motY = 0;
		if (collides(bounds.offset(motX, motY, motZ), currentCollisions))
			motX = motY = motZ = 0;
	}

	private boolean collides(BoundingBox bounds, Set<Block> ignore) {
		Set<Block> found = world.getCollidingBlocks(bounds);
		found.removeAll(ignore);
		return !found.isEmpty();
	}

	public double distanceToGround() {
		int standingOnY = (int) Math.floor(y);// floor or ceil?

		int groundY = distToGround(x, standingOnY, z);
		groundY = Math.max(groundY, distToGround(x + 0.3, standingOnY, z + 0.3));
		groundY = Math.max(groundY, distToGround(x + 0.3, standingOnY, z - 0.3));
		groundY = Math.max(groundY, distToGround(x - 0.3, standingOnY, z + 0.3));
		groundY = Math.max(groundY, distToGround(x - 0.3, standingOnY, z - 0.3));

		// double d = y - groundY;
		// System.out.println(d + " from the ground.");
		return y - groundY;
	}

	public int distToGround(double dx, int y, double dz) {
		int x = (int) Math.floor(dx), z = (int) Math.floor(dz);
		while (y > 0) {
			int id = world.getBlockData(new BlockLocation(x, (y - 1), z));
			if (id == 0) {
				y--;
				continue;
			}
			BlockData data = world.lookupBlockData(BlockId.create(id));
			if (data == null) {
				y--;
				continue;
			}
			if (data.isSolid())
				break;
			y--;
		}
		return y;
	}

	private void resetMotion() {
		motX = 0;
		motY = 0;
		motZ = 0;
	}

	@Override
	public void setX(double x) {
		this.x = x;
		resetMotion();
	}

	@Override
	public void setY(double y) {
		this.y = y;
		resetMotion();
	}

	@Override
	public void setZ(double z) {
		this.z = z;
		resetMotion();
	}

	@Override
	public void setLocation(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		resetMotion();
	}

	@Override
	public void setLocation(BlockLocation loc) {
		x = loc.getX();
		y = loc.getY();
		z = loc.getZ();
		resetMotion();
	}

	@Override
	public void setLocation(PreciseLocation loc) {
		x = loc.getX();
		y = loc.getY();
		z = loc.getZ();
		resetMotion();
	}

	@Override
	public void setHealth(float health) {
		this.health = health;
		System.out.println("set health: " + health);
		if (health <= 0) {
			bus.dispatch(new RequestRespawnEvent());
		}
	}

	public PlayerInventory getInventory() {
		return inventory;
	}

	public void setInventory(PlayerInventory inventory) {
		this.inventory = inventory;
	}

	public int getHunger() {
		return hunger;
	}

	public void setHunger(int hunger) {
		this.hunger = hunger;
	}

	public float getFoodSaturation() {
		return foodSaturation;
	}

	public void setFoodSaturation(float foodSaturation) {
		this.foodSaturation = foodSaturation;
	}

	public int getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(int experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	public int getExperienceTotal() {
		return experienceTotal;
	}

	public void setExperienceTotal(int experienceTotal) {
		this.experienceTotal = experienceTotal;
	}

	public Inventory getWindow() {
		return window;
	}

	public void setWindow(Inventory window) {
		this.window = window;
	}

	public double getLastX() {
		return lastX;
	}

	public void setLastX(double lastX) {
		this.lastX = lastX;
	}

	public double getLastY() {
		return lastY;
	}

	public void setLastY(double lastY) {
		this.lastY = lastY;
	}

	public double getLastZ() {
		return lastZ;
	}

	public void setLastZ(double lastZ) {
		this.lastZ = lastZ;
	}

	public double getLastYaw() {
		return lastYaw;
	}

	public void setLastYaw(double lastYaw) {
		this.lastYaw = lastYaw;
	}

	public double getLastPitch() {
		return lastPitch;
	}

	public void setLastPitch(double lastPitch) {
		this.lastPitch = lastPitch;
	}

	public void closeWindow() {
		if (window != null) {
			window.close();
			window = null;
		}
	}

	public void face(BlockLocation target) {
		face(target.getX() + 0.5, target.getY() + 0.5, target.getZ() + 0.5);
	}

	public void face(BlockLocation target, int face) {
		BlockLocation offset = getOffsetBlock(target, face);
		if (offset == null) {
			face(target);
			return;
		}
		double x = offset.getX() + ((target.getX() - offset.getX()) / 2.0D) + 0.5;
		double y = offset.getY() + ((target.getY() - offset.getY()) / 2.0D);
		double z = offset.getZ() + ((target.getZ() - offset.getZ()) / 2.0D) + 0.5;
		face(x, y, z);
	}

	private BlockLocation getOffsetBlock(BlockLocation location, int face) {
		int x = location.getX(), y = location.getY(), z = location.getZ();
		switch (face) {
			case 0:
				return new BlockLocation(x, y + 1, z);
			case 1:
				return new BlockLocation(x, y - 1, z);
			case 2:
				return new BlockLocation(x, y, z + 1);
			case 3:
				return new BlockLocation(x, y, z - 1);
			case 4:
				return new BlockLocation(x + 1, y, z);
			case 5:
				return new BlockLocation(x - 1, y, z);
			default:
				return null;
		}
	}

	public void face(double x, double y, double z) {
		yaw = MathHelper.calcYaw(this.x, this.z, x, z);
		pitch = MathHelper.calcPitch(this.x, this.y, this.z, x, y, z);
	}

	public BoundingBox getBoundingBox() {
		return getBoundingBoxAt(x, y, z);
	}

	public BoundingBox getBoundingBoxAt(BlockLocation location) {
		return getBoundingBoxAt(location.getX(), location.getY(), location.getZ());
	}

	public BoundingBox getBoundingBoxAt(double x, double y, double z) {
		return BoundingBox.create(x - (width / 2.0), y - (height / 2.0), z - (width / 2.0), x + (width / 2.0), y + (height / 2.0), z + (width / 2.0));
	}

	@Override
	public boolean isOnGround() {
		return distanceToGround() < 0.08;
	}

	public boolean isInLiquid() {
		return isInWater() || isInLava();
	}

	public boolean isInWater() {
		return world.isInBlock(getBoundingBox(), "Water");
	}

	public boolean isInLava() {
		return world.isInBlock(getBoundingBox(), "Lava");
	}

	@Override
	public void setCrouching(boolean crouching) {
		if (crouching != isCrouching) {
			super.setCrouching(crouching);
			controller.crouch(crouching);
		}
	}

	@Override
	public void setSprinting(boolean sprinting) {
		if (sprinting != isSprinting) {
			super.setSprinting(sprinting);
			controller.sprint(sprinting);
		}
	}

	public void swingArm() {
		// bus.dispatch(new ArmSwingEvent());
	}

	public boolean switchTools(ToolType tool) {
		LocalPlayer player = world.getLocalPlayer();
		if (player == null)
			return false;
		PlayerInventory inventory = getInventory();
		ItemStack bestTool = null;
		int bestToolSlot = -1, bestToolValue = -1;
		for (int i = 0; i < 36; i++) {
			ItemStack item = inventory.getItemAt(i);
			if (tool == null) {
				if (i > 8)
					break;
				if ((item == null) || (ToolType.getById(item.getId()) == null)) {
					bestTool = item;
					bestToolSlot = i;
					break;
				}
				continue;
			}
			if (item == null)
				continue;
			ToolType toolType = ToolType.getById(item.getId());
			if ((toolType == null) || (toolType != tool))
				continue;
			int toolValue = getToolPriority(item.getId());
			if ((bestTool == null) || (toolValue > bestToolValue)) {
				bestTool = item;
				bestToolSlot = i;
				bestToolValue = toolValue;
			}
		}
		if (bestToolSlot == -1)
			return false;
		return switchHeldItems(bestToolSlot);
	}

	private int getToolPriority(int id) {
		ToolType type = ToolType.getById(id);
		if (type == null)
			return 0;
		int[] ids = type.getIds();
		for (int i = 0; i < ids.length; i++)
			if (id == ids[i])
				return i + 1;
		return 0;
	}

	public boolean switchHeldItems(int newSlot) {
		if (inventory.getCurrentHeldSlot() == newSlot)
			return true;
		if (newSlot > 8) {
			int hotbarSpace = 9;
			for (int hotbarIndex = 0; hotbarIndex < 9; hotbarIndex++) {
				ItemStack item = inventory.getItemAt(hotbarIndex);
				if (item == null) {
					hotbarSpace = hotbarIndex;
					break;
				} else if ((ToolType.getById(item.getId()) == null) && (hotbarIndex < hotbarSpace))
					hotbarSpace = hotbarIndex;
			}
			if (hotbarSpace == 9)
				hotbarSpace = 0;
			inventory.selectItemAt(newSlot);
			inventory.selectItemAt(hotbarSpace);
			if (inventory.getSelectedItem() != null)
				inventory.selectItemAt(newSlot);
			inventory.close();
			newSlot = hotbarSpace;
		}
		inventory.setCurrentHeldSlot(newSlot);
		return true;
	}

	public void hit(Entity entity) {
		// bus.dispatch(new ArmSwingEvent());
		// bus.dispatch(new EntityHitEvent(entity));
	}

	public void use(Entity entity) {
		// bus.dispatch(new ArmSwingEvent());
		// bus.dispatch(new EntityUseEvent(entity));
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		super.updateMetadata(metadata);

		if (metadata.containsKey(10)) {
			System.out.println("le view " + metadata.get(10) + " " + metadata.get(10).getClass());
		}

		System.out.println("i got: " + Arrays.toString(metadata.keySet().toArray()));
	}
}