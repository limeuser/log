package com.mjoys.common.log;

import java.util.Map;

public class PlaceHold {
	public final static String replace(String old, Map<String, Object> context) {
		for (String key : context.keySet()) {
			Object value = context.get(key);
			old = replace(old, key, value.toString());
		}
		
		return old;
	}
	
	public final static String replace(String old, String key, Object value) {
		return old.replace("{" + key + "}", value.toString());
	}
}
