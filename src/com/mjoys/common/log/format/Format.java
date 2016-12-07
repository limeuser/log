package com.mjoys.common.log.format;

import com.mjoys.common.log.LogEntry;

public interface Format {
	String format(LogEntry entry);
}
