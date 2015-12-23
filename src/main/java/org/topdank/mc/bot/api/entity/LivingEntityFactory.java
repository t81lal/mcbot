package org.topdank.mc.bot.api.entity;

import org.topdank.mc.bot.api.BotContext;
import org.topdank.mc.bot.api.entity.living.ArmourStandEntity;
import org.topdank.mc.bot.api.entity.living.LivingEntity;
import org.topdank.mc.bot.api.entity.living.ageable.BatEntity;
import org.topdank.mc.bot.api.entity.living.ageable.ChickenEntity;
import org.topdank.mc.bot.api.entity.living.ageable.CowEntity;
import org.topdank.mc.bot.api.entity.living.ageable.HorseEntity;
import org.topdank.mc.bot.api.entity.living.ageable.MushroomCowEntity;
import org.topdank.mc.bot.api.entity.living.ageable.PigEntity;
import org.topdank.mc.bot.api.entity.living.ageable.RabbitEntity;
import org.topdank.mc.bot.api.entity.living.ageable.SheepEntity;
import org.topdank.mc.bot.api.entity.living.ageable.SnowManEntity;
import org.topdank.mc.bot.api.entity.living.ageable.SquidEntity;
import org.topdank.mc.bot.api.entity.living.ageable.VillagerEntity;
import org.topdank.mc.bot.api.entity.living.ageable.tameable.OcelotEntity;
import org.topdank.mc.bot.api.entity.living.ageable.tameable.WolfEntity;
import org.topdank.mc.bot.api.entity.living.monsters.BlazeEntity;
import org.topdank.mc.bot.api.entity.living.monsters.CaveSpiderEntity;
import org.topdank.mc.bot.api.entity.living.monsters.CreeperEntity;
import org.topdank.mc.bot.api.entity.living.monsters.EnderDragonEntity;
import org.topdank.mc.bot.api.entity.living.monsters.EndermanEntity;
import org.topdank.mc.bot.api.entity.living.monsters.EndermiteEntity;
import org.topdank.mc.bot.api.entity.living.monsters.GhastEntity;
import org.topdank.mc.bot.api.entity.living.monsters.GiantZombieEntity;
import org.topdank.mc.bot.api.entity.living.monsters.GuardianEntity;
import org.topdank.mc.bot.api.entity.living.monsters.IronGolemEntity;
import org.topdank.mc.bot.api.entity.living.monsters.MagmaCubeEntity;
import org.topdank.mc.bot.api.entity.living.monsters.SilverFishEntity;
import org.topdank.mc.bot.api.entity.living.monsters.SkeletonEntity;
import org.topdank.mc.bot.api.entity.living.monsters.SlimeEntity;
import org.topdank.mc.bot.api.entity.living.monsters.SpiderEntity;
import org.topdank.mc.bot.api.entity.living.monsters.WitchEntity;
import org.topdank.mc.bot.api.entity.living.monsters.WitherEntity;
import org.topdank.mc.bot.api.entity.living.monsters.ZombieEntity;
import org.topdank.mc.bot.api.entity.living.monsters.ZombiePigmanEntity;

public class LivingEntityFactory {

	public LivingEntity create(BotContext cxt, int type, int id) throws IllegalArgumentException {
		switch (type) {
			case 30:
				return new ArmourStandEntity(cxt.getWorld(), id);
			case 50:
				return new CreeperEntity(cxt.getWorld(), id);
			case 51:
				return new SkeletonEntity(cxt.getWorld(), id);
			case 52:
				return new SpiderEntity(cxt.getWorld(), id);
			case 53:
				return new GiantZombieEntity(cxt.getWorld(), id);
			case 54:
				return new ZombieEntity(cxt.getWorld(), id);
			case 55:
				return new SlimeEntity(cxt.getWorld(), id);
			case 56:
				return new GhastEntity(cxt.getWorld(), id);
			case 57:
				return new ZombiePigmanEntity(cxt.getWorld(), id);
			case 58:
				return new EndermanEntity(cxt.getWorld(), id);
			case 59:
				return new CaveSpiderEntity(cxt.getWorld(), id);
			case 60:
				return new SilverFishEntity(cxt.getWorld(), id);
			case 61:
				return new BlazeEntity(cxt.getWorld(), id);
			case 62:
				return new MagmaCubeEntity(cxt.getWorld(), id);
			case 63:
				return new EnderDragonEntity(cxt.getWorld(), id);
			case 64:
				return new WitherEntity(cxt.getWorld(), id);
			case 65:
				return new BatEntity(cxt.getWorld(), id);
			case 66:
				return new WitchEntity(cxt.getWorld(), id);
			case 67:
				return new EndermiteEntity(cxt.getWorld(), id);
			case 68:
				return new GuardianEntity(cxt.getWorld(), id);
			case 90:
				return new PigEntity(cxt.getWorld(), id);
			case 91:
				return new SheepEntity(cxt.getWorld(), id);
			case 92:
				return new CowEntity(cxt.getWorld(), id);
			case 93:
				return new ChickenEntity(cxt.getWorld(), id);
			case 94:
				return new SquidEntity(cxt.getWorld(), id);
			case 95:
				return new WolfEntity(cxt.getWorld(), id);
			case 96:
				return new MushroomCowEntity(cxt.getWorld(), id);
			case 97:
				return new SnowManEntity(cxt.getWorld(), id);
			case 98:
				return new OcelotEntity(cxt.getWorld(), id);
			case 99:
				return new IronGolemEntity(cxt.getWorld(), id);
			case 100:
				return new HorseEntity(cxt.getWorld(), id);
			case 101:
				return new RabbitEntity(cxt.getWorld(), id);
			case 120:
				return new VillagerEntity(cxt.getWorld(), id);
			default:
				throw new IllegalArgumentException("Invalid LivingEntity typeId: " + type);
		}
	}
}