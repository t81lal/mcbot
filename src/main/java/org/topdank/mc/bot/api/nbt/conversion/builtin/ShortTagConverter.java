package org.topdank.mc.bot.api.nbt.conversion.builtin;

import org.topdank.mc.bot.api.nbt.conversion.TagConverter;
import org.topdank.mc.bot.api.nbt.tag.builtin.ShortTag;

/**
 * A converter that converts between ShortTag and short.
 */
public class ShortTagConverter implements TagConverter<ShortTag, Short> {

	@Override
	public Short convert(ShortTag tag) {
		return tag.getValue();
	}

	@Override
	public ShortTag convert(String name, Short value) {
		return new ShortTag(name, value);
	}

}
