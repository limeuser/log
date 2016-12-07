package com.mjoys.common.log;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.mjoys.common.log.filter.LevelFilter;
import com.mjoys.common.log.format.DateObjectFormat;
import com.mjoys.common.log.format.Format;
import com.mjoys.common.log.format.ObjectFormat;
import com.mjoys.common.log.format.PlaceHoldFormat;
import com.mjoys.common.log.wrapper.DateWrapper;
import com.mjoys.common.log.wrapper.HostWrapper;
import com.mjoys.common.log.wrapper.SourceCodeWrapper;
import com.mjoys.common.log.writer.ConsoleWritter;

public class Default {
	public final static Format Format;
	
	public final static Map<String, ObjectFormat> ObjectFormat = new HashMap<String, ObjectFormat>();
	
	public final static DateObjectFormat DateFormat = new DateObjectFormat(new SimpleDateFormat("YYYY/MM/dd-hh:mm:ss"));
	
	//public final static SourceCodeObjectFormat SourceCodeObjectFormat = new SourceCodeObjectFormat(String.format("{%s}:{%s}:{%s}", SourceCode.FileKey, SourceCode.RowKey, SourceCode.ColKey)); 
	
	public final static String[] Keys = new String[]{"level", "msg", DateWrapper.Key, SourceCodeWrapper.Key, HostWrapper.Key};
	
	public final static String FormatString = "{level} : {date} : {host} : {source} : {msg}";
	
	public final static LevelFilter LevelFilter = new LevelFilter(Level.error);
	
	public final static Cfg Cfg = new Cfg();
	
	static {
		ObjectFormat.put(DateWrapper.Key, DateFormat);
		//ObjectFormat.put(SourceCodeWrapper.Key, SourceCodeObjectFormat);
		
		Format = new PlaceHoldFormat(FormatString, ObjectFormat);
		
		Cfg
		.format(Format)
		.addFilter(LevelFilter)
		.addWrapper(new DateWrapper())
		.addWrapper(new SourceCodeWrapper())
		.addWrapper(new HostWrapper())
		.addWriter(new ConsoleWritter());
	}
}
