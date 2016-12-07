package com.mjoys.common.log.wrapper;

import com.mjoys.common.log.LogEntry;

public interface Wrapper {
	String key();
	LogEntry wrap(LogEntry entry);
}