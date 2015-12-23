package org.topdank.mc.bot.api.nbt.conversion.builtin.custom;

import java.io.Serializable;

import org.topdank.mc.bot.api.nbt.conversion.TagConverter;
import org.topdank.mc.bot.api.nbt.tag.builtin.custom.SerializableArrayTag;

/**
 * A converter that converts between SerializableArrayTag and Serializable[].
 */
public class SerializableArrayTagConverter implements TagConverter<SerializableArrayTag, Serializable[]> {

	@Override
	public Serializable[] convert(SerializableArrayTag tag) {
		return tag.getValue();
	}

	@Override
	public SerializableArrayTag convert(String name, Serializable[] value) {
		return new SerializableArrayTag(name, value);
	}

}
