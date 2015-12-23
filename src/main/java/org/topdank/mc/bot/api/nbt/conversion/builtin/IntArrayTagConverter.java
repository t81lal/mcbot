package org.topdank.mc.bot.api.nbt.conversion.builtin;

import org.topdank.mc.bot.api.nbt.conversion.TagConverter;
import org.topdank.mc.bot.api.nbt.tag.builtin.IntArrayTag;

/**
 * A converter that converts between IntArrayTag and int[].
 */
public class IntArrayTagConverter implements TagConverter<IntArrayTag, int[]> {

	@Override
	public int[] convert(IntArrayTag tag) {
		return tag.getValue();
	}

	@Override
	public IntArrayTag convert(String name, int[] value) {
		return new IntArrayTag(name, value);
	}

}
