package com.ygm.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by admin on 2018/11/3.
 * 服务端处理类
 */
public class Serverhandler implements Runnable{

   private Socket socket;

    public Serverhandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try{

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            String content;
            String result;
            while(true){
                // 通过BufferedReader读取一行
                // 如果已经读到输入流尾部，返回null,退出循环
                // 如果得到非空值，就尝试计算结果并返回
                if ((content = in.readLine()) == null) break;
                System.out.println("服务端收到信息" + content);

                out.println(content);

            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }finally{
            // 一些必要工作处理
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 赋值为空有助于更好的垃圾回收
            in = null ;

            if (out != null){
                out.close();
                out = null;
            }

            if (socket != null){
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
