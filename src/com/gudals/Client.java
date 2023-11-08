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
        Scanner scanner = new Scanner(System.in);
        String msg = "";
        String name = "";
        try {
            sock = new Socket("localhost", 5050);

            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            pw = new PrintWriter(sock.getOutputStream());

            System.out.print("이름을 입력하시오 > ");
            name = scanner.nextLine();

            pw.println(name);
            pw.flush();

            ClientThread clientThread = new ClientThread(name, sock);
            Thread thread = new Thread(clientThread);
            thread.start();

            while (true){
                msg = scanner.nextLine();
                pw.println("["+name+"] : "+msg);
                pw.flush();
            }// while

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(scanner != null) scanner.close();
                if(pw != null) pw.close();
                if(br != null) br.close();
                if(sock != null) sock.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }// finally
    }// main
}// class
