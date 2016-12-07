package com.mjoys.common.log.format;

import java.text.DateFormat;
import java.util.Date;

public class DateObjectFormat implements ObjectFormat {
	private DateFormat format;
	public DateObjectFormat(DateFormat dateFormat) {
		this.format = dateFormat;
	}
	
	@Override
	public String format(Object t) {
		return this.format.format((Date) t);
	}
}
