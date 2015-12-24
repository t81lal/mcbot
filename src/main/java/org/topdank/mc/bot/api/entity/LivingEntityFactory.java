package org.topdank.mc.bot.api.entity;

import org.topdank.mc.bot.api.BotContext;
import org.topdank.mc.bot.impl.entity.Entity;
import org.topdank.mc.bot.impl.entity.impl.living.ArmourStandEntity;
import org.topdank.mc.bot.impl.entity.impl.living.BatEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.VillagerEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal.ChickenEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal.CowEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal.HorseEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal.MushroomCowEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal.PigEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal.RabbitEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal.SheepEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal.tameable.OcelotEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.ageable.animal.tameable.WolfEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.golem.IronGolemEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.golem.SnowManEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.BlazeEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.CaveSpiderEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.CreeperEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.EnderDragonEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.EndermanEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.EndermiteEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.GhastEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.GiantZombieEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.GuardianEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.MagmaCubeEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.SilverFishEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.SkeletonEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.SlimeEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.SpiderEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.SquidEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.WitchEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.WitherEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.ZombieEntity;
import org.topdank.mc.bot.impl.entity.impl.living.creature.mob.ZombiePigmanEntity;

public class LivingEntityFactory {

	public Entity create(BotContext cxt, int type, int id) throws IllegalArgumentException {
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