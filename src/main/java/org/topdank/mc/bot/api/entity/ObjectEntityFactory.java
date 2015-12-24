package org.topdank.mc.bot.api.entity;

import org.topdank.mc.bot.api.BotContext;
import org.topdank.mc.bot.impl.entity.Entity;
import org.topdank.mc.bot.impl.entity.impl.ArrowEntity;
import org.topdank.mc.bot.impl.entity.impl.BoatEntity;
import org.topdank.mc.bot.impl.entity.impl.EnderCrystalEntity;
import org.topdank.mc.bot.impl.entity.impl.EnderEyeEntity;
import org.topdank.mc.bot.impl.entity.impl.FallingBlockEntity;
import org.topdank.mc.bot.impl.entity.impl.FireworkEntity;
import org.topdank.mc.bot.impl.entity.impl.FishingBobEntity;
import org.topdank.mc.bot.impl.entity.impl.ItemEntity;
import org.topdank.mc.bot.impl.entity.impl.PrimedTNTEntity;
import org.topdank.mc.bot.impl.entity.impl.fireball.LargeFireBallEntity;
import org.topdank.mc.bot.impl.entity.impl.fireball.SmallFireBallEntity;
import org.topdank.mc.bot.impl.entity.impl.fireball.WitherSkullEntity;
import org.topdank.mc.bot.impl.entity.impl.hanging.ItemFrameEntity;
import org.topdank.mc.bot.impl.entity.impl.hanging.LeashKnotEntity;
import org.topdank.mc.bot.impl.entity.impl.living.LivingEntity;
import org.topdank.mc.bot.impl.entity.impl.living.player.PlayerEntity;
import org.topdank.mc.bot.impl.entity.impl.minecart.CommandBlockMinecart;
import org.topdank.mc.bot.impl.entity.impl.minecart.FurnaceMinecart;
import org.topdank.mc.bot.impl.entity.impl.minecart.MinecartEntity;
import org.topdank.mc.bot.impl.entity.impl.minecart.TNTMinecartEntity;
import org.topdank.mc.bot.impl.entity.impl.minecart.MinecartEntity.MinecartType;
import org.topdank.mc.bot.impl.entity.impl.projectile.EggEntity;
import org.topdank.mc.bot.impl.entity.impl.projectile.EnderpearlEntity;
import org.topdank.mc.bot.impl.entity.impl.projectile.ExpBottleEntity;
import org.topdank.mc.bot.impl.entity.impl.projectile.PotionEntity;
import org.topdank.mc.bot.impl.entity.impl.projectile.SnowBallEntity;

public class ObjectEntityFactory {

	public Entity create(BotContext cxt, int type, int id, int data, double x, double y, double z, float pitch, float yaw, double motx, double moty, double motz) {
		Entity entity = null;
		
		switch (type) {
			case 10: {
				MinecartType cartType = MinecartType.byNetworkID(data);
				switch(cartType) {
					case COMMAND_BLOCK:
						entity = new CommandBlockMinecart(cxt.getWorld(), id);
					case FURNACE:
						entity = new FurnaceMinecart(cxt.getWorld(), id);
					case TNT:
						entity = new TNTMinecartEntity(cxt.getWorld(), id);
					default:
						entity = new MinecartEntity(cxt.getWorld(), id, cartType);
				}
				break;
			}
			case 90: {
				Entity thrower = cxt.getWorld().getEntityById(data);
				if(thrower instanceof PlayerEntity) {
					entity = new FishingBobEntity(cxt.getWorld(), id, thrower);
				} else {
					throw new UnsupportedOperationException("Only players can throw fish hooks.");
				}
				data = 0;
				break;
			}
			case 60: {
				entity = new ArrowEntity(cxt.getWorld(), id);
				break;
			}
			case 61: {
				entity = new SnowBallEntity(cxt.getWorld(), id);
				break;
			}
			case 71: {
				entity = new ItemFrameEntity(cxt.getWorld(), id);
				data = 0;
				break;
			}
			case 77: {
				entity = new LeashKnotEntity(cxt.getWorld(), id);
				data = 0;
				break;
			}
			case 65: {
				entity = new EnderpearlEntity(cxt.getWorld(), id);
				break;
			}
			case 72: {
				entity = new EnderEyeEntity(cxt.getWorld(), id);
				break;
			}
			case 76: {
				entity = new FireworkEntity(cxt.getWorld(), id);
				break;
			}
			case 63: {
				entity =  new LargeFireBallEntity(cxt.getWorld(), id);
				entity.setMotion(motx, moty, motz);
				data = 0;
				break;
			}
			case 64: {
				entity =  new SmallFireBallEntity(cxt.getWorld(), id);
				entity.setMotion(motx, moty, motz);
				data = 0;
				break;
			}
			case 66: {
				entity =  new WitherSkullEntity(cxt.getWorld(), id);
				entity.setMotion(motx, moty, motz);
				data = 0;
				break;
			}
			case 62: {
				entity = new EggEntity(cxt.getWorld(), id);
				break;
			}
			case 73: {
				// data = potion itemstack meta
				entity = new PotionEntity(cxt.getWorld(), id);
				data = 0;
				break;
			}
			case 75: {
				entity = new ExpBottleEntity(cxt.getWorld(), id);
				data = 0;
				break;
			}
			case 1: {
				entity = new BoatEntity(cxt.getWorld(), id);
				break;
			}
			case 50: {
				entity = new PrimedTNTEntity(cxt.getWorld(), id);
				break;
			}
			case 78: {
				entity = new ArrowEntity(cxt.getWorld(), id);
				break;
			}
			case 51: {
				entity = new EnderCrystalEntity(cxt.getWorld(), id);
				break;
			}
			case 2: {
				entity = new ItemEntity(cxt.getWorld(), id);
				break;
			}
			case 70: {
				int packed = data & 65535;
				int bId = packed & 4095;
		        int bMeta = packed >> 12 & 15;
				entity = new FallingBlockEntity(cxt.getWorld(), id, bId, bMeta);
				data = 0;
				break;
			}
			default:
				throw new IllegalArgumentException("Invalid ObjectEntity typeId: " + type);
		}
			
		entity.setLocation(x, y, z);
		entity.setPitch(pitch);
		entity.setYaw(yaw);
		
		if(data > 0) {
			if(type == 60) {
				Entity other = cxt.getWorld().getEntityById(data);
				if(other instanceof LivingEntity || entity instanceof ArrowEntity) {
					((ArrowEntity) entity).setShooter(other);
				}
			}
			entity.setMotion(motx, moty, motz);
		}
		return entity;
	}
}