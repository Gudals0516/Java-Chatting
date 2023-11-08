package com.gudals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable{
    private Socket sock = null;
    BufferedReader br = null;
    PrintWriter pw = null;
    String name = "";
    String inmsg = "";
    public ClientThread(String name, Socket sock){
        try {
            this.sock = sock;
            this.name = name;
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }// ClientThread
    @Override
    public void run(){
        try {
            while (true){
                inmsg = br.readLine();
                System.out.println(inmsg);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }// run
}// class
