package com.gudals;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket servsock = null;
        Socket sock = null;
        try {
            servsock = new ServerSocket(5050);
            while (true){
                System.out.println("서버 대기중");
                sock = servsock.accept();
                System.out.println("접속 완료 : " + sock.getInetAddress());

                ServerThread serverThread = new ServerThread(sock);
                Thread thread = new Thread(serverThread);
                thread.start();
            }//while
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(sock!=null) sock.close();
                if(servsock != null) servsock.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }// finally
    }// main
}// class
