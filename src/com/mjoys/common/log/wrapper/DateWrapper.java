package com.mjoys.common.log.wrapper;

import java.util.Date;

import com.mjoys.common.log.LogEntry;

public class DateWrapper implements Wrapper {
	public final static String Key = "date";
	
	@Override
	public LogEntry wrap(LogEntry entry) {
		entry.context.put(Key, new Date());
		return entry;
	}

	@Override
	public String key() {
		return Key;
	}
}
