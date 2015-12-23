package org.topdank.mc.bot.api.nbt.conversion.builtin;

import java.util.HashMap;
import java.util.Map;

import org.topdank.mc.bot.api.nbt.conversion.ConverterRegistry;
import org.topdank.mc.bot.api.nbt.conversion.TagConverter;
import org.topdank.mc.bot.api.nbt.tag.builtin.CompoundTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.Tag;

/**
 * A converter that converts between CompoundTag and Map.
 */
@SuppressWarnings("rawtypes")
public class CompoundTagConverter implements TagConverter<CompoundTag, Map> {
	
	@Override
	public Map convert(CompoundTag tag) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Tag> tags = tag.getValue();
		for (String name : tags.keySet()) {
			Tag t = tags.get(name);
			ret.put(t.getName(), ConverterRegistry.convertToValue(t));
		}
		
		return ret;
	}
	
	@Override
	public CompoundTag convert(String name, Map value) {
		Map<String, Tag> tags = new HashMap<String, Tag>();
		for (Object na : value.keySet()) {
			String n = (String) na;
			tags.put(n, ConverterRegistry.convertToTag(n, value.get(n)));
		}
		
		return new CompoundTag(name, tags);
	}
	
}
