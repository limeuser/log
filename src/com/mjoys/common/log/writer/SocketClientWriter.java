package com.mjoys.common.log.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.locks.Lock;

public class SocketClientWriter implements Writer {
	private String host;
	private int port;
	private String code;
	
	private Socket socket;
	private OutputStream out;
	
	private Lock lock;
	
	private byte[] lineSeparator;
	
	public SocketClientWriter(String host, int port, String code) throws IOException {
		this.host = host;
		this.port = port;
		this.code = code;
		
		this.lineSeparator = System.getProperty("line.separator").getBytes(code);
		
		this.socket = new Socket();
		
		new Thread("reconnect") {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					
					try {
						connect();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		connect();
	}
	
	private void connect() throws IOException {
		this.lock.lock();
		if (this.out == null) {
			this.lock.unlock();
			
			this.socket.connect(new InetSocketAddress(host, port));
			
			this.lock.lock();
			try {
				this.out = socket.getOutputStream();
			} finally {
				this.lock.unlock();
			}
		} else {
			this.lock.unlock();
		}
	}
	
	@Override
	public void writeLine(String msg) throws IOException {
		this.lock.lock();
		
		if (this.out == null) {
			this.lock.unlock();
			return;
		}
		
		this.lock.unlock();
		
		this.out.write(msg.getBytes(this.code));
		this.out.write(lineSeparator);
		this.out.flush();
	}
	
	@Override
	public void close() throws IOException {
		this.lock.lock();
		if (this.out != null) {
			try {
				this.out.close();
				this.socket.close();
			} catch (IOException e) {
				throw e;
			} finally {
				this.out = null;
				this.lock.unlock();
			}
		} else {
			this.lock.unlock();
		}
	}
}
