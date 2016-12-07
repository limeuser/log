package com.mjoys.common.log;

import org.apache.log4j.Logger;

import com.mjoys.common.util.RateLimiter;

public class RateLimitedLogger {
	private Logger logger;
	private RateLimiter limiter;

	public RateLimitedLogger(Logger logger, RateLimiter limiter) {
		this.logger = logger;
		this.limiter = limiter;
	}

	private boolean notLimited() {
		return !limiter.limited();
	}

	public RateLimiter getRateLimiter() {
		return this.limiter;
	}

	public RateLimitedLogger debug(Object msg) {
		if (notLimited())
			logger.debug(msg);

		return this;
	}

	public RateLimitedLogger debug(Object msg, Throwable e) {
		if (notLimited())
			logger.debug(msg, e);

		return this;
	}

	public RateLimitedLogger trace(Object msg) {
		if (notLimited())
			logger.trace(msg);

		return this;
	}

	public RateLimitedLogger trace(Object msg, Throwable e) {
		if (notLimited())
			logger.trace(msg, e);
		return this;
	}

	public RateLimitedLogger info(Object msg) {
		if (notLimited())
			logger.info(msg);
		return this;
	}

	public RateLimitedLogger info(Object msg, Throwable e) {
		if (notLimited())
			logger.info(msg, e);
		return this;
	}

	public RateLimitedLogger warn(Object msg) {
		if (notLimited())
			logger.warn(msg);
		return this;
	}

	public RateLimitedLogger warn(Object msg, Throwable e) {
		if (notLimited())
			logger.warn(msg, e);
		return this;
	}

	public RateLimitedLogger error(Object msg) {
		if (notLimited())
			logger.error(msg);
		return this;
	}

	public RateLimitedLogger error(Object msg, Throwable e) {
		if (notLimited())
			logger.error(msg, e);
		return this;
	}

	public RateLimitedLogger fatal(Object msg) {
		if (notLimited())
			logger.fatal(msg);
		return this;
	}

	public RateLimitedLogger fatal(Object msg, Throwable e) {
		if (notLimited())
			logger.fatal(msg, e);
		return this;
	}
}
