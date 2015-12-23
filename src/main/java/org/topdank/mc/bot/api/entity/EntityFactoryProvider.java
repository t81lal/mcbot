package org.topdank.mc.bot.api.entity;

public class EntityFactoryProvider {

	private LivingEntityFactory livingEntityFactory;
	private ObjectEntityFactory objectEntityFactory;
	private TileEntityFactory tileEntityFactory;
	
	public EntityFactoryProvider(LivingEntityFactory livingEntityFactory, ObjectEntityFactory objectEntityFactory, TileEntityFactory tileEntityFactory) {
		this.livingEntityFactory = livingEntityFactory;
		this.objectEntityFactory = objectEntityFactory;
		this.tileEntityFactory = tileEntityFactory;
	}

	public LivingEntityFactory getLivingEntityFactory() {
		return livingEntityFactory;
	}

	public void setLivingEntityFactory(LivingEntityFactory livingEntityFactory) {
		this.livingEntityFactory = livingEntityFactory;
	}

	public ObjectEntityFactory getObjectEntityFactory() {
		return objectEntityFactory;
	}

	public void setObjectEntityFactory(ObjectEntityFactory objectEntityFactory) {
		this.objectEntityFactory = objectEntityFactory;
	}

	public TileEntityFactory getTileEntityFactory() {
		return tileEntityFactory;
	}

	public void setTileEntityFactory(TileEntityFactory tileEntityFactory) {
		this.tileEntityFactory = tileEntityFactory;
	}
}