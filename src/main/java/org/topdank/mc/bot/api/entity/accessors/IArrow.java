package org.topdank.mc.bot.api.entity.accessors;

public interface IArrow extends IEntity {

	IEntity getShooter();
	
	void setShooter(IEntity shooter);
	
	boolean isCritical();
	
	void setCritical(boolean crit);
}