package com.gudals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket sock = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        String outmsg = null;
        String name = null;

        ChatFrame chatFrame = new ChatFrame();
        chatFrame.viewon();

        try {
            sock = new Socket("localhost", 5050);

            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            pw = new PrintWriter(sock.getOutputStream());

            while (true){
                if(name==null){
                    name = chatFrame.outmsg;
                }else if(name!=null){
                    chatFrame.outmsg = null;
                    break;
                }
            }// while

            pw.println(name);
            pw.flush();

            ClientThread clientThread = new ClientThread(name, sock, chatFrame);
            Thread thread = new Thread(clientThread);
            thread.start();

            while (true){
                outmsg = chatFrame.outmsg;
                if(outmsg==null){
                    continue;
                }else if(outmsg!=null) {
                    pw.println("[" + name + "] : " + outmsg);
                    pw.flush();
                    chatFrame.outmsg = null;
                    continue;
                }
            }// while

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(pw != null) pw.close();
                if(br != null) br.close();
                if(sock != null) sock.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }// finally
    }// main
}// class
