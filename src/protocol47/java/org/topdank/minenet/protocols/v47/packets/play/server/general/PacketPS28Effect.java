package org.topdank.minenet.protocols.v47.packets.play.server.general;

import java.io.IOException;

import org.topdank.bot.net.io.ReadableInput;
import org.topdank.bot.net.packet.IdentifiableReadablePacket;
import org.topdank.minenet.protocols.v47.Protocol47DataHelper;

public class PacketPS28Effect implements IdentifiableReadablePacket {

	public PacketPS28Effect() {
	}

	@Override
	public void read(ReadableInput in) throws IOException {
		int id = in.readInt();
		if (id >= 2000) {
			// particle
		} else {
			// soundeffect
		}
		Protocol47DataHelper.decodePosition(in);
		in.readInt();
		// if (effect == SoundEffect.PLAY_RECORD) {
		// data = new RecordEffectData(value);
		// } else if (effect == ParticleEffect.SMOKE) {
		// data = MagicValues.key(SmokeEffectData.class, value);
		// } else if (effect == ParticleEffect.BREAK_BLOCK) {
		// data = new BreakBlockEffectData(value);
		// } else if (effect == ParticleEffect.BREAK_SPLASH_POTION) {
		// data = new BreakPotionEffectData(value);
		// } else if (effect == ParticleEffect.HARD_LANDING_DUST) {
		// data = new HardLandingEffectData(value);
		// }
		in.readBoolean();
	}

	@Override
	public boolean isPriorityPacket() {
		return false;
	}

	@Override
	public int getId() {
		return 0x28;
	}
}