package org.topdank.mc.bot.api.nbt.conversion.builtin.custom;

import java.io.Serializable;

import org.topdank.mc.bot.api.nbt.conversion.TagConverter;
import org.topdank.mc.bot.api.nbt.tag.builtin.custom.SerializableTag;

/**
 * A converter that converts between SerializableTag and Serializable.
 */
public class SerializableTagConverter implements TagConverter<SerializableTag, Serializable> {

	@Override
	public Serializable convert(SerializableTag tag) {
		return tag.getValue();
	}

	@Override
	public SerializableTag convert(String name, Serializable value) {
		return new SerializableTag(name, value);
	}

}
