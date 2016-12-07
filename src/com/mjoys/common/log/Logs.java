package com.mjoys.common.log;

import com.mjoys.common.log.filter.RateLimitFilter;
import com.mjoys.common.util.RateLimiter;

public class Logs {
	private static Logger root = new Log(Default.Cfg);
	
	private static PrefixTree<String, Logger> tree = new PrefixTree<String, Logger>("", root);
	
	public final static void setRoot(Logger logger) {
		root = logger;
		tree = new PrefixTree<String, Logger>("", root);
	}
	
	public final static void add(String packageName, Logger logger) {
		tree.insert(packageName.split("\\."), logger);
	}
	
	public final static void add(Class<?> cls, Logger logger) {
		add(cls.getName(), logger);
	}
	
	public final static Logger get(String packageName) {
		return tree.get(packageName.split("\\."));
	}
	
	public final static Logger get(Class<?> cls) {
		return get(cls.getName());
	}
	
	public final static Logger getRateLimitedLog(Log log, RateLimiter limiter) {
		return new Log(log.cfg().clone().addFilter(new RateLimitFilter(limiter)));
	}
	
	public final static Logger getRateLimitedLogger(Logger logger, RateLimiter limiter) {
		return getRateLimitedLog((Log) logger, limiter);
	}
	
	public final static Logger get() {
		return root;
	}
}
