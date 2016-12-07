package com.mjoys.common.log.filter;

import com.mjoys.common.log.LogEntry;

public class EnableFilter implements Filter {
	public boolean enable;
	public EnableFilter(boolean enable) {
		this.enable = enable;
	}
	
	@Override
	public boolean isFiltered(LogEntry entry) {
		return this.enable == false;
	}
}
