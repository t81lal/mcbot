package org.topdank.bot.eventbus.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class ReflectionHelper {

	public static final Filter<Method> ACCEPT_ALL = new Filter<Method>() {
		@Override
		public boolean accept(Method m) {
			return true;
		}
	};

	public final static List<Class<?>> getSuperClasses(Class<?> c) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		classes.add(c);
		while (c != null) {
			c = c.getSuperclass();
			if ((c == null) || c.getClass().equals(Object.class))
				break;
			classes.add(c);
		}
		return classes;
	}

	public final static List<Method> collateMethods(Class<?> c, Filter<Method> filter) {
		List<Method> methods = new ArrayList<Method>();
		Method[] ms = c.getDeclaredMethods();
		for (Method m : ms)
			methods.add(m);
		return methods;
	}

	public final static List<Method> collateMethods(Class<?> c) {
		return collateMethods(c, ACCEPT_ALL);
	}

	public final static List<Method> deepCollateMethods(Class<?> c) {
		List<Class<?>> classes = getSuperClasses(c);
		List<Method> methods = new ArrayList<Method>();
		for (Class<?> c1 : classes) {
			methods.addAll(collateMethods(c1));
		}
		return methods;
	}

	public static interface Filter<T> {
		public abstract boolean accept(T t);
	}
}