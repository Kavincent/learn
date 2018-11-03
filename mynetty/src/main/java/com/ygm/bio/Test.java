package com.ygm.bio;

import java.io.IOException;
import java.util.Random;

/**
 * Created by admin on 2018/11/3.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        //运行服务器
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BIOServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //防止客户端先于服务器启动前执行代码
        Thread.sleep(100);

        final Random random = new Random(System.currentTimeMillis());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    Client1.send(String.valueOf(random.nextInt(10)));
                    try {
                        Thread.sleep(random.nextInt(1000));
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    Client2.send(String.valueOf(random.nextInt(10)));
                    try {
                        Thread.sleep(random.nextInt(1000));
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
