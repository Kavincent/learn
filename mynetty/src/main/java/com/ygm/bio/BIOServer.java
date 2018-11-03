package com.ygm.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by admin on 2018/11/3.
 * BIO服务端
 */
public class BIOServer {
    // 默认端口号
    public static int DEFAULT_PORT = 8888;
    // 单例的ServerSocket
    private static ServerSocket serverSocket;

    /**
     * 根据传入参数设置监听端口，如果没有参数调用以下方法并使用默认值
     */
    public static void start() throws IOException {
        // 使用默认值
        start(DEFAULT_PORT);
    }

    /**
     * 此方法不会被大量并发访问，不太需要考虑效率，直接进方法同步就行
     * @param port
     * @throws IOException
     */
    public synchronized static void start(int port) throws IOException {
        if (serverSocket != null) return;

        try{

            serverSocket = new ServerSocket(port);
            System.out.println("服务端口已启动，端口号：" + port);

            // 通过无限循环监听客户端连接
            // 如果端口合法且空闲，将阻塞在accept操作上
            while(true){
                final Socket socket = serverSocket.accept();
                new Thread(new Serverhandler(socket)).start();

            }
        }finally{
            // 一些必要清理工作
            if(serverSocket != null){
                System.out.println("服务端已关闭");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
