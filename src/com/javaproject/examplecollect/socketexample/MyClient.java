package com.javaproject.examplecollect.socketexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient {
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 12345);
		//由线程读取
		new Thread(new ClientRunable(socket)).start();
		
		PrintStream print = new PrintStream(socket.getOutputStream());
		String message = "";
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		
		while ((message = read.readLine()) != null) {
			print.println(message);
		}
		
        
	}
}
