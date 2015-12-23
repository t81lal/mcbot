package org.topdank.mc.bot.api.nbt.conversion.builtin;

import org.topdank.mc.bot.api.nbt.conversion.TagConverter;
import org.topdank.mc.bot.api.nbt.tag.builtin.DoubleTag;

/**
 * A converter that converts between DoubleTag and double.
 */
public class DoubleTagConverter implements TagConverter<DoubleTag, Double> {

	@Override
	public Double convert(DoubleTag tag) {
		return tag.getValue();
	}

	@Override
	public DoubleTag convert(String name, Double value) {
		return new DoubleTag(name, value);
	}

}
