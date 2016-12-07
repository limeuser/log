package com.mjoys.common.log;

import java.util.ArrayList;
import java.util.List;

import com.mjoys.common.log.filter.Filter;
import com.mjoys.common.log.format.Format;
import com.mjoys.common.log.translator.Translator;
import com.mjoys.common.log.wrapper.Wrapper;
import com.mjoys.common.log.writer.Writer;

public class Cfg {
	private Format format;
	private List<Wrapper> wrappers = new ArrayList<Wrapper>();
	private List<Translator> translators = new ArrayList<Translator>();
	private List<Filter> filters = new ArrayList<Filter>();
	private List<Writer> writers = new ArrayList<Writer>();
	
	public Cfg clone() {
		Cfg cfg = new Cfg();
		
		cfg.format = this.format;
		cfg.wrappers.addAll(this.wrappers);
		cfg.translators.addAll(this.translators);
		cfg.filters.addAll(this.filters);
		cfg.writers.addAll(this.writers);
		
		return cfg;
	}
	
	public Format format() {
		return this.format;
	}
	
	public List<Wrapper> wrappers() {
		return this.wrappers;
	}
	
	public List<Translator> translators() {
		return this.translators;
	}
	
	public List<Filter> filters() {
		return this.filters;
	}
	
	public List<Writer> writers() {
		return this.writers;
	}
	
	public Cfg format(Format format) {
		this.format = format;
		return this;
	}
	public Cfg addWrapper(Wrapper w) {
		this.wrappers.add(w);
		return this;
	}
	
	public Cfg addTranslator(Translator t) {
		this.translators.add(t);
		return this;
	}
	
	public Cfg addFilter(Filter f) {
		this.filters.add(f);
		return this;
	}
	
	public Cfg addWriter(Writer w) {
		this.writers.add(w);
		return this;
	}
	
	public Cfg clearFilters() {
		this.filters.clear();
		return this;
	}
	
	public Cfg clearTranslators() {
		this.translators.clear();
		return this;
	}
	
	public Cfg clearWriters() {
		this.writers.clear();
		return this;
	}
	
	public Cfg clearWrappers() {
		this.wrappers.clear();
		return this;
	}
	
	public Cfg clear() {
		this.filters.clear();
		this.translators.clear();
		this.writers.clear();
		this.wrappers.clear();
		return this;
	}
}
