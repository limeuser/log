package com.mjoys.common.log;

import java.util.HashMap;
import java.util.Map;

public class LogEntry {
	public String msg;
	public Level level;
	public Map<String, Object> context = new HashMap<String, Object>();
}
