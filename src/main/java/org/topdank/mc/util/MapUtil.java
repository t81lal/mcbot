package org.topdank.mc.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {

	public static Map<String, Object> create(Object[] objs) {
		if((objs.length % 2) == 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			for(int i=0; i < objs.length; i+=2) {
				String key = String.valueOf(objs[i]);
				Object val = objs[i + 1];
				map.put(key, val);
			}
			return map;
		} else {
			return null;
		}
	}
}