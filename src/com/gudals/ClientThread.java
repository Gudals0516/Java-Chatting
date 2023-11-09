package com.gudals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable{
    private Socket sock = null;
    BufferedReader br = null;
    String name = "";
    String inmsg = "";
    ChatFrame chatFrame = null;
    public ClientThread(String name, Socket sock, ChatFrame chatFrame){
        try {
            this.sock = sock;
            this.name = name;
            this.chatFrame = chatFrame;
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
                chatFrame.addmsg(inmsg);
                System.out.println(inmsg);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }// run
}// class
