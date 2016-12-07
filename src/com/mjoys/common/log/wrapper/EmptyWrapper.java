package com.mjoys.common.log.wrapper;

import com.mjoys.common.log.LogEntry;

public class EmptyWrapper implements Wrapper {
	private EmptyWrapper() {}
	public final static EmptyWrapper Instance = new EmptyWrapper();
	
	@Override
	public LogEntry wrap(LogEntry entry) {
		return entry;
	}

	@Override
	public String key() {
		return "";
	}
}
