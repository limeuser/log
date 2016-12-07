package com.mjoys.common.log.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter implements Writer {
	private String fileName;
	private boolean rolling;
	private long max;
	private long size;
	private String code;
	
	private String rollingFileName;
	
	private byte[] lineSeparator;
	
	private FileOutputStream writer;
	
	public FileWriter(String fileName) throws IOException {
		this(fileName, false, 0, "UTF8");
	}
	
	public FileWriter(String fileName, String code) throws IOException {
		this(fileName, false, 0, code);
	}
	
	public FileWriter(String fileName, boolean rolling, long max, String code) throws IOException {
		this.fileName = fileName;
		this.rolling = rolling;
		this.max = max;
		this.code = code;
		
		this.rollingFileName = this.fileName;
		
		File file = new File(this.fileName);
		if (file.exists() == false) {
			file.createNewFile();
		}
		
		this.writer = new FileOutputStream(file, true);
		this.lineSeparator = System.getProperty("line.separator").getBytes(code);
	}
	
	@Override
	public void writeLine(String msg) throws IOException {
		if (this.writer == null) {
			File file = new File(this.fileName);
			if (file.exists() == false) {
				file.createNewFile();
			}
			
			this.writer = new FileOutputStream(file, true);
		}
		
		byte[] bytes = msg.getBytes(this.code);
		this.size += bytes.length;
		this.size += this.lineSeparator.length;
		
		this.writer.write(bytes);
		this.writer.write(lineSeparator);
		
		this.writer.flush();
		
		rolling();
	}
	
	private void rolling() throws IOException {
		if (this.rolling) {
			if (this.size >= this.max) {
				this.writer.close();
				
				if (this.rollingFileName.equals(this.fileName)) {
					this.rollingFileName += ".rolling";
				} else {
					this.rollingFileName = this.fileName;
				}
				
				File file = new File(this.rollingFileName);
				if (file.exists() == false) {
					file.createNewFile();
				}
				
				this.writer = new FileOutputStream(new File(this.rollingFileName), false);
			}
		}
	}
	
	@Override
	public void close() throws IOException {
		try {
			this.writer.close();
		} finally {
			this.writer = null;
		}
	}
}
