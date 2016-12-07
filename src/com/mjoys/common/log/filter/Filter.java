package com.mjoys.common.log.filter;

import com.mjoys.common.log.LogEntry;

public interface Filter {
	boolean isFiltered(LogEntry entry);
}