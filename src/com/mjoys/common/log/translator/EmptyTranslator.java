package com.mjoys.common.log.translator;

import com.mjoys.common.log.LogEntry;

public class EmptyTranslator implements Translator {
	private EmptyTranslator() {}
	public final static EmptyTranslator Instance = new EmptyTranslator();
	
	@Override
	public String translate(LogEntry entry, String msg) {
		return msg;
	}
}
