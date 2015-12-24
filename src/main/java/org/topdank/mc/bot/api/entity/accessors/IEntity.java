package org.topdank.mc.bot.api.entity.accessors;

import java.util.Map;

import org.topdank.mc.bot.api.entity.EntityProperty;
import org.topdank.mc.bot.api.world.World;
import org.topdank.mc.bot.api.world.pos.BlockLocation;
import org.topdank.mc.bot.api.world.pos.PreciseLocation;

public interface IEntity {

	int getId();
	
	World getWorld();
	
	String getNameTag();
	
	boolean shouldRenderNameTag();
	
	boolean isOnFire();
	
	boolean isCrouching();
	
	boolean isSprinting();
	
	boolean isRightClicking();
	
	boolean isInvisible();
	
	boolean isSilent();
	
	int getBreathTimer();
	
	float getWidth();
	
	float getHeight();
	
	double getX();
	
	double getY();
	
	double getZ();
	
	PreciseLocation getLocation();
	
	void setX(double x);
	
	void setY(double y);
	
	void setZ(double z);
	
	default void setLocation(double x, double y, double z) {
		setX(x);
		setY(y);
		setZ(z);
	}
	
	void setLocation(BlockLocation loc);
	
	void setLocation(PreciseLocation loc);
	
	double getMotionX();
	
	double getMotionY();
	
	double getMotionZ();
	
	void setMotionX(double mx);
	
	void setMotionY(double my);
	
	void setMotionZ(double mz);
	
	default void setMotion(double mx, double my, double mz) {
		setMotionX(mx);
		setMotionY(my);
		setMotionZ(mz);
	}
	
	float getYaw();
	
	float getPitch();
	
	float getHeadYaw();
	
	void setYaw(float yaw);
	
	void setPitch(float pitch);
	
	void setHeadYaw(float headYaw);
	
	default void setRotation(float yaw, float pitch, float headYaw) {
		setYaw(yaw);
		setPitch(pitch);
		setHeadYaw(headYaw);
	}
	
	IEntity getRiding();
	
	IEntity getRider();
	
	void setRiding(IEntity riding);
	
	void setRider(IEntity rider);
	
	boolean isDead();
	
	void setDead(boolean dead);
	
	boolean isOnGround();
	
	void updateMetadata(Map<Integer, Object> metadata);
	
	void updateProperty(Map<String, EntityProperty> properties);
	
	void updateStatus(int id);
}