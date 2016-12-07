package com.mjoys.common.log.wrapper;

import com.mjoys.common.log.LogEntry;
import com.mjoys.common.log.SourceCode;

public class SourceCodeWrapper implements Wrapper {
	public final static String Key = "source";
	
	@Override
	public LogEntry wrap(LogEntry entry) {
		StackTraceElement stack = new Exception().getStackTrace()[3];
		entry.context.put(Key, new SourceCode(stack.getFileName(), stack.getClassName(), stack.getMethodName(), stack.getLineNumber(), 0));
		return entry;
	}

	@Override
	public String key() {
		return Key;
	}
}
