package org.topdank.minenet.protocols.v47.packets.play.server.world;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;

public class PacketPS2ASpawnParticle implements IdentifiableReadablePacket {

	public PacketPS2ASpawnParticle() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		Particle p = Particle.values()[in.readInt()];
		in.readBoolean();
		in.readFloat();
		in.readFloat();
		in.readFloat();
		in.readFloat();
		in.readFloat();
		in.readFloat();
		in.readFloat();
		in.readInt();
		int len = p.getDataLength();
		for (int index = 0; index < len; index++) {
			in.readVarInt();
		}
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x2A;
	}

	public enum Particle {

		EXPLOSION_NORMAL,
		EXPLOSION_LARGE,
		EXPLOSION_HUGE,
		FIREWORKS_SPARK,
		WATER_BUBBLE,
		WATER_SPLASH,
		WATER_WAKE,
		SUSPENDED,
		SUSPENDED_DEPTH,
		CRIT,
		CRIT_MAGIC,
		SMOKE_NORMAL,
		SMOKE_LARGE,
		SPELL,
		SPELL_INSTANT,
		SPELL_MOB,
		SPELL_MOB_AMBIENT,
		SPELL_WITCH,
		DRIP_WATER,
		DRIP_LAVA,
		VILLAGER_ANGRY,
		VILLAGER_HAPPY,
		TOWN_AURA,
		NOTE,
		PORTAL,
		ENCHANTMENT_TABLE,
		FLAME,
		LAVA,
		FOOTSTEP,
		CLOUD,
		REDSTONE,
		SNOWBALL,
		SNOW_SHOVEL,
		SLIME,
		HEART,
		BARRIER,
		ICON_CRACK(2),
		BLOCK_CRACK(1),
		BLOCK_DUST(1),
		WATER_DROP,
		ITEM_TAKE,
		MOB_APPEARANCE;

		private int dataLength;

		private Particle() {
			this(0);
		}

		private Particle(int dataLength) {
			this.dataLength = dataLength;
		}

		public int getDataLength() {
			return dataLength;
		}
	}
}