package org.topdank.mc.bot.impl.entity;

import java.util.Map;

import org.topdank.mc.bot.api.entity.EntityProperty;
import org.topdank.mc.bot.api.entity.accessors.IEntity;
import org.topdank.mc.bot.api.world.WatchableObject;
import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.mc.bot.api.world.pos.PreciseLocation;

public class Entity extends WatchableObject implements IEntity {

	protected final World world;
	
	protected String nametag;
	protected boolean shouldRenderNametag;
	
	protected boolean isOnFire;
	protected boolean isCrouching;
	protected boolean isSprinting;
	protected boolean isRightClicking;
	protected boolean isInvisible;
	protected boolean isSilent;
	
	protected int breathTimer;

	protected float width;
	protected float height;

	protected double x;
	protected double y;
	protected double z;
	protected double motX;
	protected double motY;
	protected double motZ;
	protected float yaw;
	protected float pitch;
	protected float headYaw;

	protected IEntity riding;
	protected IEntity rider;

	protected boolean isDead;
	protected boolean isOnGround;

	public Entity(World world, int id, float defaultWidth, float defaultHeight) {
		super(id);
		this.world = world;
		width = defaultWidth;
		height = defaultHeight;
	}
	
	@Override
	public World getWorld() {
		return world;
	}

	@Override
	public String getNameTag() {
		return nametag;
	}

	public void setNameTag(String nametag) {
		this.nametag = nametag;
	}

	@Override
	public boolean shouldRenderNameTag() {
		return shouldRenderNametag;
	}

	public void setShouldRenderNameTag(boolean shouldRenderNametag) {
		this.shouldRenderNametag = shouldRenderNametag;
	}

	@Override
	public boolean isOnFire() {
		return isOnFire;
	}

	public void setOnFire(boolean isOnFire) {
		this.isOnFire = isOnFire;
	}

	@Override
	public boolean isCrouching() {
		return isCrouching;
	}

	public void setCrouching(boolean isCrouching) {
		this.isCrouching = isCrouching;
	}

	@Override
	public boolean isSprinting() {
		return isSprinting;
	}

	public void setSprinting(boolean isSprinting) {
		this.isSprinting = isSprinting;
	}

	@Override
	public boolean isRightClicking() {
		return isRightClicking;
	}

	public void setRightClicking(boolean isRightClicking) {
		this.isRightClicking = isRightClicking;
	}

	@Override
	public boolean isInvisible() {
		return isInvisible;
	}

	public void setInvisible(boolean isInvisible) {
		this.isInvisible = isInvisible;
	}
	
	@Override
	public boolean isSilent() {
		return isSilent;
	}
	
	public void setSilent(boolean isSilent) {
		this.isSilent = isSilent;
	}

	@Override
	public int getBreathTimer() {
		return breathTimer;
	}

	public void setBreathTimer(int breathTimer) {
		this.breathTimer = breathTimer;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public double getZ() {
		return z;
	}

	@Override
	public void setZ(double z) {
		this.z = z;
	}

	@Override
	public void setLocation(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void setLocation(BlockLocation loc) {
		x = loc.getX();
		y = loc.getY();
		z = loc.getZ();
	}

	@Override
	public void setLocation(PreciseLocation loc) {
		x = loc.getX();
		y = loc.getY();
		z = loc.getZ();
	}

	@Override
	public PreciseLocation getLocation() {
		return new PreciseLocation(x, y, z);
	}

	@Override
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setSize(float w, float h) {
		width = w;
		height = h;
	}
	
	@Override
	public double getMotionX() {
		return motX;
	}

	@Override
	public void setMotionX(double motX) {
		this.motX = motX;
		// if ((motX == 0) && (motY == 0) && (motZ == 0)) {
		// world.unwatch(this);
		// } else {
		// world.watch(this);
		// }
	}

	@Override
	public double getMotionY() {
		return motY;
	}

	@Override
	public void setMotionY(double motY) {
		this.motY = motY;
		// if ((motX == 0) && (motY == 0) && (motZ == 0)) {
		// world.unwatch(this);
		// } else {
		// world.watch(this);
		// }
	}

	@Override
	public double getMotionZ() {
		return motZ;
	}

	@Override
	public void setMotionZ(double motZ) {
		this.motZ = motZ;
		// if ((motX == 0) && (motY == 0) && (motZ == 0)) {
		// world.unwatch(this);
		// } else {
		// world.watch(this);
		// }
	}

	@Override
	public void setMotion(double x, double y, double z) {
		motX = x;
		motY = y;
		motZ = z;

		// if ((motX == 0) && (motY == 0) && (motZ == 0)) {
		// world.unwatch(this);
		// } else {
		// world.watch(this);
		// }
	}

	@Override
	public float getYaw() {
		return yaw;
	}

	@Override
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	@Override
	public float getHeadYaw() {
		return headYaw;
	}

	@Override
	public void setHeadYaw(float headYaw) {
		this.headYaw = headYaw;
	}

	@Override
	public float getPitch() {
		return pitch;
	}

	@Override
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	@Override
	public IEntity getRiding() {
		return riding;
	}

	@Override
	public void setRiding(IEntity riding) {
		this.riding = riding;
	}

	@Override
	public IEntity getRider() {
		return rider;
	}

	@Override
	public void setRider(IEntity rider) {
		this.rider = rider;
	}

	@Override
	public boolean isDead() {
		return isDead;
	}

	@Override
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	@Override
	public boolean isOnGround() {
		return isOnGround;
	}

	public void setOnGround(boolean isOnGround) {
		this.isOnGround = isOnGround;
	}

	@Override
	public void updateMetadata(Map<Integer, Object> metadata) {
		if (metadata.containsKey(0)) {
			byte flags = (byte) metadata.get(0);
			setOnFire((flags & 0x01) != 0);
			setCrouching((flags & 0x02) != 0);
			setSprinting((flags & 0x08) != 0);
			setRightClicking((flags & 0x10) != 0);
			setInvisible((flags & 0x20) != 0);
		}
		
		if (metadata.containsKey(1)) {
			setBreathTimer((short) metadata.get(1));
		}
		
		if(metadata.containsKey(2)) {
			setNameTag((String) metadata.get(2));
		}
		
		if(metadata.containsKey(3)) {
			setShouldRenderNameTag(((byte) metadata.get(3)) == 1);
		}
		
		if(metadata.containsKey(4)) {
			setSilent(((byte) metadata.get(4)) == 1);
		}
	}

	@Override
	public void updateProperty(Map<String, EntityProperty> properties) {
	}
	
	@Override
	public void updateStatus(int id) {
	}

	@Override
	public void update() {
		if ((motX >= -1E-6) && (motX <= 1E-6))
			motX = 0;
		if ((motY >= -1E-6) && (motY <= 1E-6))
			motY = 0;
		if ((motZ >= -1E-6) && (motZ <= 1E-6))
			motZ = 0;

		x += motX;
		y += motY;
		z += motZ;
	}

	public void accelerate(double horizAngle, double vertAngle, double accel) {
		motX += accel * Math.cos(horizAngle) * Math.cos(vertAngle);
		motZ += accel * Math.sin(horizAngle) * Math.cos(vertAngle);
		motY += accel * Math.sin(vertAngle);
	}

	public void accelerate(double horizAngle, double vertAngle, double accel, double velocityBound) {
		double ax = Math.abs(accel * Math.cos(horizAngle) * Math.cos(vertAngle));
		double az = Math.abs(accel * Math.sin(horizAngle) * Math.cos(vertAngle));
		double ay = Math.abs(accel * Math.sin(vertAngle));
		double vxb = velocityBound * Math.cos(horizAngle) * Math.cos(vertAngle);
		double vzb = velocityBound * Math.sin(horizAngle) * Math.cos(vertAngle);
		double vyb = velocityBound * Math.sin(vertAngle);
		if ((vxb < 0) && (motX > vxb))
			motX = Math.max(vxb, motX - ax);
		else if ((vxb > 0) && (motX < vxb))
			motX = Math.min(vxb, motX + ax);
		if ((vzb < 0) && (motZ > vzb))
			motZ = Math.max(vzb, motZ - az);
		else if ((vzb > 0) && (motZ < vzb))
			motZ = Math.min(vzb, motZ + az);
		if ((vyb < 0) && (motY > vyb))
			motY = Math.max(vyb, motY - ay);
		else if ((vyb > 0) && (motY < vyb))
			motY = Math.min(vyb, motY + ay);
	}
}