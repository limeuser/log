package com.mjoys.common.log.format;

import java.util.HashMap;
import java.util.Map;

import com.mjoys.common.log.LogEntry;
import com.mjoys.common.log.PlaceHold;

public class PlaceHoldFormat implements Format {
	private String format;
	private Map<String, ObjectFormat> objectFormats = new HashMap<String, ObjectFormat>();
	
	public PlaceHoldFormat(String format) {
		this.format = format;
	}
	
	public PlaceHoldFormat(String format, Map<String, ObjectFormat> objectFormats) {
		this.format = format;
		this.objectFormats = objectFormats;
	}
	
	@Override
	public String format(LogEntry entry) {
		String msg = this.format;
		
		msg = PlaceHold.replace(msg, "msg", entry.msg);
		msg = PlaceHold.replace(msg, "level", entry.level);
		
		for (String key : entry.context.keySet()) {
			String value = entry.context.get(key).toString();
			if (this.objectFormats.get(key) != null) {
				value = this.objectFormats.get(key).format(entry.context.get(key));	
			}
			
			msg = PlaceHold.replace(msg, key, value); 
		}

		return msg;
	}
}
