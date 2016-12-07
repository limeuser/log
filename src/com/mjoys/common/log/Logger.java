package com.mjoys.common.log;

public interface Logger {
	void debug(String format, Object ...args);
	void trace(String format, Object ...args);
	void info(String format, Object ...args);
	void warn(String format, Object ...args);
	void error(String format, Object ...args);
	void bug(String format, Object ...args);
	void fatal(String format, Object ...args);
	void exception(Throwable e, String format, Object ...args);
	void exception(Throwable e, boolean printStack, String format, Object ...args);
	void exception(Throwable e, boolean printStack);
	void exception(Throwable e);
}
