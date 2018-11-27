package com.javaproject.examplecollect.socketexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerRunable implements Runnable {
	
	private Socket socket = null;
	private BufferedReader reader = null;
	
	public ServerRunable(Socket socket){
		this.socket = socket;
		try {
			this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		String line = null;
		try {
			while ((line= getContext()) != null) {
				for (Socket sock : MyServer.list) {
						if (sock != this.socket) {
							PrintStream print = new PrintStream(sock.getOutputStream());
							print.println(line);
						}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getContext(){
		String inline = null;
		try {
			inline = reader.readLine();
		} catch (IOException e) {
			MyServer.list.remove(socket);
		}
		return inline;
	}	
}
