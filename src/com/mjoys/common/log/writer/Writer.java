package com.mjoys.common.log.writer;

import java.io.IOException;

public interface Writer {
	void writeLine(String msg) throws IOException;
	void close() throws IOException;
}
