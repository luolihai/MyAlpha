package com.javaproject.examplecollect.socketexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRunable implements Runnable {

    private Socket socket;  
    private BufferedReader read;  
  
    public ClientRunable(Socket socket) throws IOException {  
        this.socket = socket;  
        this.read = new BufferedReader(new InputStreamReader(  
                this.socket.getInputStream()));  
    }  
  
    @Override  
    public void run() {  
        try{  
            String line=null;  
            while((line=read.readLine())!=null){  
                System.out.println(line);  
            }  
              
        }catch(IOException  io){  
            io.printStackTrace();  
        }  
  
    }  
  
}
