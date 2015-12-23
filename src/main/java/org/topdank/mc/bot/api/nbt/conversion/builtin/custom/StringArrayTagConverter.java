package org.topdank.mc.bot.api.nbt.conversion.builtin.custom;

import org.topdank.mc.bot.api.nbt.conversion.TagConverter;
import org.topdank.mc.bot.api.nbt.tag.builtin.StringArrayTag;

/**
 * A converter that converts between StringArrayTag and String[].
 */
public class StringArrayTagConverter implements TagConverter<StringArrayTag, String[]> {

	@Override
	public String[] convert(StringArrayTag tag) {
		return tag.getValue();
	}

	@Override
	public StringArrayTag convert(String name, String[] value) {
		return new StringArrayTag(name, value);
	}

}
