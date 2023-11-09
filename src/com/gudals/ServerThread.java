package com.gudals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread implements Runnable{
    static ArrayList<ServerThread> list = new ArrayList<>();
    private Socket sock = null;
    private BufferedReader br = null;
    PrintWriter pw = null;
    private String name ="";
    public ServerThread(Socket sock){
        try {
            this.sock = sock;
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            pw = new PrintWriter(sock.getOutputStream());
            list.add(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }// ServerThread

    @Override
    public void run(){
        try {
            name = br.readLine();
            broadCasting("["+name+"]님이 입장하셨습니다.");
            while (true){
                String msg = br.readLine();
                broadCasting(msg);
            }// while
        } catch (IOException e) {
            broadCasting("System : ["+name+"]님이 퇴장하셨습니다.\n");
        }finally{
            try {
                sock.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }// finally
    }// run
    private void broadCasting(String msg){
       for(ServerThread serverThread: list){
           serverThread.pw.println(msg);
           serverThread.pw.flush();
       }
    }// broadCasting
}// class
