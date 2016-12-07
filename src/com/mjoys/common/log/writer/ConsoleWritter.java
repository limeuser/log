package com.mjoys.common.log.writer;

import java.io.IOException;

public class ConsoleWritter implements Writer {
	@Override
	public void writeLine(String msg) {
		System.out.println(msg);
	}

	@Override
	public void close() throws IOException {
	}
}
