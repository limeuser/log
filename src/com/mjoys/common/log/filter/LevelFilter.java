package com.mjoys.common.log.filter;

import com.mjoys.common.log.Level;
import com.mjoys.common.log.LogEntry;

public class LevelFilter implements Filter {
	private Level level;
	public LevelFilter(Level level) {
		this.level = level;
	}
	
	@Override
	public boolean isFiltered(LogEntry entry) {
		if (entry.level.compareTo(level) < 0) {
			return true;
		}
		
		return false;
	}
}
