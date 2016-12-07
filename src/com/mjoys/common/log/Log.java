package com.mjoys.common.log;

import java.io.IOException;

import com.mjoys.common.log.filter.Filter;
import com.mjoys.common.log.translator.Translator;
import com.mjoys.common.log.wrapper.Wrapper;
import com.mjoys.common.log.writer.Writer;

public class Log extends BaseLogger {
	private Cfg cfg;
	
	public Log(Cfg config) {
		this.cfg = config;
	}
	
	public Cfg cfg() {
		return this.cfg;
	}
	
	@Override
	public void write(Level level, String msg) {
		LogEntry entry = new LogEntry();
		entry.msg = msg;
		entry.level = level;

		// set context
		for (Wrapper wrapper : cfg.wrappers()) {
			entry = wrapper.wrap(entry);
		}
		
		// filter
		for (Filter filter : cfg.filters()) {
			if (filter.isFiltered(entry)) {
				return;
			}
		}
		
		// format
		String finalMsg = msg;
		if (cfg.format() != null) {
			finalMsg = cfg.format().format(entry);
		}
		
		// translate
		for (Translator t : cfg.translators()) {
			finalMsg = t.translate(entry, finalMsg);
		}
		
		for (Writer writer : cfg.writers()) {
			try {
				writer.writeLine(finalMsg);
			} catch (IOException e) {
				try {
					writer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	}
}
