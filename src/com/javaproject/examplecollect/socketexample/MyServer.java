package com.javaproject.examplecollect.socketexample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * socket服务端
 * 一个客户端向多个客户端发消息，
 * 哎，注意print.println(...)呀
 * 
 * 会堵塞，可以不断循环
 * serverSocket.accept();
 * reader.readLine();
 * 
 * @author llh
 * @version 1.0.0
 */
public class MyServer {

	public static List<Socket> list = new ArrayList<Socket>();

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(12345);
		while (true) {
			Socket socket = serverSocket.accept();
			list.add(socket);
			//根据scoket信息，发送到其它socket
			new Thread(new ServerRunable(socket)).start();
		}
	}
}
