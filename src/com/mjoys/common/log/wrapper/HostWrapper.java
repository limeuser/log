package com.mjoys.common.log.wrapper;

import com.mjoys.common.log.LogEntry;
import com.mjoys.common.util.OSUtil;

public class HostWrapper implements Wrapper {
	public final static String Key = "host";
	
	@Override
	public String key() {
		return Key;
	}

	@Override
	public LogEntry wrap(LogEntry entry) {
		entry.context.put(Key, OSUtil.getHostName());
		return entry;
	}

}
