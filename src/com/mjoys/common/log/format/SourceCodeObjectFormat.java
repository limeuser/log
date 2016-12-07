package com.mjoys.common.log.format;

import com.mjoys.common.log.PlaceHold;
import com.mjoys.common.log.SourceCode;

public class SourceCodeObjectFormat implements ObjectFormat {
	private String format;
	public SourceCodeObjectFormat(String format) {
		this.format = format;
	}
	
	@Override
	public String format(Object t) {
		SourceCode s = (SourceCode) t;
		return PlaceHold.replace(this.format, s.toMap());
	}
}