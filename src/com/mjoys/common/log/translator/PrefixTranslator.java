package com.mjoys.common.log.translator;

import com.mjoys.common.log.LogEntry;

public class PrefixTranslator implements Translator {
	private String prefix;
	public PrefixTranslator(String prefix) {
		this.prefix = prefix;
	}
	
	@Override
	public String translate(LogEntry entry, String msg) {
		return prefix + msg;
	}
}
