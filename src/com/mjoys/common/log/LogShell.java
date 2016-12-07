package com.mjoys.common.log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;

import com.mjoys.common.net.Address;
import com.mjoys.common.net.SocketClient;
import com.mjoys.common.util.NumberUtil;

public class LogShell {
    private SocketClient socketClient;
    public final static int DefaultPort = 8000;
    
    private static LogShell shell;
    
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("必须指定服务器地址和端口号");
            return;
        }
        
        String addr = args[0];
        String portStr = args[1];
        Integer port = NumberUtil.parseInt(portStr);
        
        if (port == null || port <= 1000 || port >= 10000) {
            System.out.println("端口无效：[1000~10000]");
            return;
        }
        
        shell = new LogShell();
        shell.start(Address.getSocketAddress("0.0.0.0", DefaultPort), Address.getSocketAddress(addr, port));
    }
    
    public boolean start(Address server) {
        return start(Address.getSocketAddress("0.0.0.0", DefaultPort), server);
    }
    
    public boolean start(Address local, Address server) {
        socketClient = new SocketClient();
        
        if (socketClient.connect(server, local) == false) {
            System.out.println("连接日志服务器失败");
            return false;
        }
        
        System.out.println("连接到日志服务器");
        
        try {
            processUserCommand();
        } catch (Exception e) {
            System.err.println("客户端处理命令异常: " + e.getMessage());
        }
        
        return true;
    }
    
    private void echoHint() {
        System.err.print("log: ");
    }
    private void echo(String msg) {
        echoHint();
        System.err.println(msg);
    }
    
    private void processUserCommand() throws IOException {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(System.in)); 
        String line;
        
        System.err.println("start read");
        while ((line = reader.readLine()) != null) {
            echoHint();
            
            String[] tokens = line.split("\\s");
            if (tokens.length < 1) {
                echo("unknown command");
                continue;
            }

            String cmd = line.trim();
            if (cmd.equals("exit") || cmd.equals("quit")) {
                disconnect();
                return;
            }
            
            sendCmd(line.trim());
        }
    }
    
    private void disconnect() {
        this.socketClient.disconnect();
    }
    
    public void sendCmd(String cmd) throws UnsupportedEncodingException, IOException {
        socketClient.getConnection().send(cmd.getBytes("UTF-8"));
    }
}
