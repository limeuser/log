package com.mjoys.common.log.translator;

import com.mjoys.common.log.LogEntry;

public interface Translator {
	String translate(LogEntry entry, String msg);
}
