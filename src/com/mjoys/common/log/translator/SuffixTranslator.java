package com.mjoys.common.log.translator;

import com.mjoys.common.log.LogEntry;

public class SuffixTranslator implements Translator {
	private String suffix;
	
	public SuffixTranslator(String suffix) {
		this.suffix = suffix;
	}
	
	@Override
	public String translate(LogEntry entry, String msg) {
		return msg + suffix;
	}
}
