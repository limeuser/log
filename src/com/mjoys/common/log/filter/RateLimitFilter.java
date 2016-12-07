package com.mjoys.common.log.filter;

import com.mjoys.common.log.LogEntry;
import com.mjoys.common.util.RateLimiter;

public class RateLimitFilter implements Filter {
	private RateLimiter limiter;
	
	public RateLimitFilter(RateLimiter limiter) {
		this.limiter = limiter;
	}
	
	@Override
	public boolean isFiltered(LogEntry entry) {
		if (limiter.limited()) {
			return true;
		}
		
		return false;
	}
}
