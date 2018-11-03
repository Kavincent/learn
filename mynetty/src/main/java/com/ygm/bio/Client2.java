package com.ygm.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by admin on 2018/11/3.
 */
public class Client2 {
    // 默认端口号
    private static int DEFAULT_SERVER_PORT = 8888;

    private static String DEFAULT_SERVER_IP = "127.0.0.1";

    public static void send(String content){

        send(DEFAULT_SERVER_PORT, content);
    }

    public static void send(int port, String content){
        System.out.println(("客户端2内容为：" + content));
        Socket socket = null;

        BufferedReader in = null;

        PrintWriter out = null;
        try{
            socket = new Socket(DEFAULT_SERVER_IP, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(content);
            System.out.println("服务端返回内容为：" + in.readLine());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (out != null){
                out.close();
                out = null;
            }
            if (socket !=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }

}