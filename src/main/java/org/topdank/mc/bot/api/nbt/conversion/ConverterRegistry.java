package org.topdank.mc.bot.api.nbt.conversion;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.topdank.mc.bot.api.nbt.conversion.builtin.ByteArrayTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.ByteTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.CompoundTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.DoubleTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.FloatTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.IntArrayTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.IntTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.ListTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.LongTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.ShortTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.StringTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.custom.DoubleArrayTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.custom.FloatArrayTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.custom.LongArrayTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.custom.SerializableArrayTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.custom.SerializableTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.custom.ShortArrayTagConverter;
import org.topdank.mc.bot.api.nbt.conversion.builtin.custom.StringArrayTagConverter;
import org.topdank.mc.bot.api.nbt.tag.TagRegisterException;
import org.topdank.mc.bot.api.nbt.tag.builtin.ByteArrayTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.ByteTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.CompoundTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.DoubleTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.FloatTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.IntArrayTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.IntTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.ListTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.LongTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.ShortTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.StringArrayTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.StringTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.Tag;
import org.topdank.mc.bot.api.nbt.tag.builtin.custom.DoubleArrayTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.custom.FloatArrayTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.custom.LongArrayTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.custom.SerializableArrayTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.custom.SerializableTag;
import org.topdank.mc.bot.api.nbt.tag.builtin.custom.ShortArrayTag;

/**
 * A registry mapping tags and value types to converters.
 */
public class ConverterRegistry {
	
	private static final Map<Class<? extends Tag>, TagConverter<? extends Tag, ?>> tagToConverter = new HashMap<Class<? extends Tag>, TagConverter<? extends Tag, ?>>();
	private static final Map<Class<?>, TagConverter<? extends Tag, ?>> typeToConverter = new HashMap<Class<?>, TagConverter<? extends Tag, ?>>();
	
	static {
		register(ByteTag.class, Byte.class, new ByteTagConverter());
		register(ShortTag.class, Short.class, new ShortTagConverter());
		register(IntTag.class, Integer.class, new IntTagConverter());
		register(LongTag.class, Long.class, new LongTagConverter());
		register(FloatTag.class, Float.class, new FloatTagConverter());
		register(DoubleTag.class, Double.class, new DoubleTagConverter());
		register(ByteArrayTag.class, byte[].class, new ByteArrayTagConverter());
		register(StringTag.class, String.class, new StringTagConverter());
		register(ListTag.class, List.class, new ListTagConverter());
		register(CompoundTag.class, Map.class, new CompoundTagConverter());
		register(IntArrayTag.class, int[].class, new IntArrayTagConverter());
		
		register(DoubleArrayTag.class, double[].class, new DoubleArrayTagConverter());
		register(FloatArrayTag.class, float[].class, new FloatArrayTagConverter());
		register(LongArrayTag.class, long[].class, new LongArrayTagConverter());
		register(SerializableArrayTag.class, Serializable[].class, new SerializableArrayTagConverter());
		register(SerializableTag.class, Serializable.class, new SerializableTagConverter());
		register(ShortArrayTag.class, short[].class, new ShortArrayTagConverter());
		register(StringArrayTag.class, String[].class, new StringArrayTagConverter());
	}
	
	/**
	 * Registers a converter.
	 *
	 * @param tag Tag type class to register the converter to.
	 * @param type Value type class to register the converter to.
	 * @param converter Converter to register.
	 * @throws ConverterRegisterException If an error occurs while registering the converter.
	 */
	public static <T extends Tag, V> void register(Class<T> tag, Class<V> type, TagConverter<T, V> converter) throws ConverterRegisterException {
		if (tagToConverter.containsKey(tag)) {
			throw new TagRegisterException("Type conversion to tag " + tag.getName() + " is already registered.");
		}
		
		if (typeToConverter.containsKey(type)) {
			throw new TagRegisterException("Tag conversion to type " + type.getName() + " is already registered.");
		}
		
		tagToConverter.put(tag, converter);
		typeToConverter.put(type, converter);
	}
	
	/**
	 * Converts the given tag to a value.
	 *
	 * @param tag Tag to convert.
	 * @return The converted value.
	 * @throw ConversionException If a suitable converter could not be found.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Tag, V> V convertToValue(T tag) throws ConversionException {
		if ((tag == null) || (tag.getValue() == null)) {
			return null;
		}
		
		if (!tagToConverter.containsKey(tag.getClass())) {
			throw new ConversionException("Tag type " + tag.getClass().getName() + " has no converter.");
		}
		
		TagConverter<T, ?> converter = (TagConverter<T, ?>) tagToConverter.get(tag.getClass());
		return (V) converter.convert(tag);
	}
	
	/**
	 * Converts the given value to a tag.
	 *
	 * @param name Name of the resulting tag.
	 * @param value Value to convert.
	 * @return The converted tag.
	 * @throw ConversionException If a suitable converter could not be found.
	 */
	@SuppressWarnings("unchecked")
	public static <V, T extends Tag> T convertToTag(String name, V value) throws ConversionException {
		if (value == null) {
			return null;
		}
		
		TagConverter<T, V> converter = (TagConverter<T, V>) typeToConverter.get(value.getClass());
		if (converter == null) {
			for (Class<?> clazz : getAllClasses(value.getClass())) {
				if (typeToConverter.containsKey(clazz)) {
					try {
						converter = (TagConverter<T, V>) typeToConverter.get(clazz);
						break;
					} catch (ClassCastException e) {
					}
				}
			}
		}
		
		if (converter == null) {
			throw new ConversionException("Value type " + value.getClass().getName() + " has no converter.");
		}
		
		return converter.convert(name, value);
	}
	
	private static Set<Class<?>> getAllClasses(Class<?> clazz) {
		Set<Class<?>> ret = new LinkedHashSet<Class<?>>();
		Class<?> c = clazz;
		while (c != null) {
			ret.add(c);
			ret.addAll(getAllSuperInterfaces(c));
			c = c.getSuperclass();
		}
		
		// Make sure Serializable is at the end to avoid mix-ups.
		if (ret.contains(Serializable.class)) {
			ret.remove(Serializable.class);
			ret.add(Serializable.class);
		}
		
		return ret;
	}
	
	private static Set<Class<?>> getAllSuperInterfaces(Class<?> clazz) {
		Set<Class<?>> ret = new HashSet<Class<?>>();
		for (Class<?> c : clazz.getInterfaces()) {
			ret.add(c);
			ret.addAll(getAllSuperInterfaces(c));
		}
		
		return ret;
	}
	
}
