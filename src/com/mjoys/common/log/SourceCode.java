package com.mjoys.common.log;

import java.util.HashMap;
import java.util.Map;

public class SourceCode {
	public String file;
	public String className;
	public String methodName;
	public int row;
	public int col;

	public SourceCode() {
		
	}
	
	public SourceCode(String file, String clsname, String method, int row, int col) {
		this.file = file;
		this.className = clsname;
		this.methodName = method;
		this.row = row;
		this.col = col;
	}
	
	public final static String FileKey = "file";
	public final static String ClassKey = "class";
	public final static String MethodKey = "method";
	public final static String RowKey = "row";
	public final static String ColKey = "col";
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put(FileKey, this.file);
		map.put(ClassKey, this.className);
		map.put(MethodKey, this.methodName);
		map.put(RowKey, this.row);
		map.put(ColKey, this.col);
		
		return map;
	}
	
	@Override
	public String toString() {
		return String.format("%s:%d:%d", this.file, this.row, this.col);
	}
}