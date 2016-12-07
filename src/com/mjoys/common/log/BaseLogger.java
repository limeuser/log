package com.mjoys.common.log;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.mjoys.common.util.OSUtil;

public abstract class BaseLogger implements Logger {
	public abstract void write(Level level, String msg);
	
	@Override
	public void debug(String format, Object... args) {
		write(Level.debug, String.format(format, args));
	}

	@Override
	public void trace(String format, Object... args) {
		write(Level.trace, String.format(format, args));
	}

	@Override
	public void info(String format, Object... args) {
		write(Level.info, String.format(format, args));
	}

	@Override
	public void warn(String format, Object... args) {
		write(Level.warn, String.format(format, args));
	}
	
	@Override
	public void error(String format, Object... args) {
		write(Level.error, String.format(format, args));
	}

	@Override
	public void bug(String format, Object... args) {
		write(Level.bug, String.format(format, args));
	}

	@Override
	public void fatal(String format, Object... args) {
		write(Level.fatal, String.format(format, args));
	}

	@Override
	public void exception(Throwable e, boolean printStack, String format, Object... args) {
		String msg = String.format(format, args) + OSUtil.LineSeparator + "cause: " + e.getMessage();
		if (printStack) {
			StringWriter s = new StringWriter();
			e.printStackTrace(new PrintWriter(s));
			msg += OSUtil.LineSeparator;
			msg += s.toString();
		}
		write(Level.error, msg);
	}

	@Override
	public void exception(Throwable e, boolean printStack) {
		exception(e, printStack, "");
	}
	
	@Override
	public void exception(Throwable e, String format, Object... args) {
		exception(e, false, format, args);
	}

	@Override
	public void exception(Throwable e) {
		exception(e, false, "");
	}
}