package org.topdank.mc.bot.api.nbt.conversion.builtin;

import org.topdank.mc.bot.api.nbt.conversion.TagConverter;
import org.topdank.mc.bot.api.nbt.tag.builtin.FloatTag;

/**
 * A converter that converts between FloatTag and float.
 */
public class FloatTagConverter implements TagConverter<FloatTag, Float> {

	@Override
	public Float convert(FloatTag tag) {
		return tag.getValue();
	}

	@Override
	public FloatTag convert(String name, Float value) {
		return new FloatTag(name, value);
	}

}
