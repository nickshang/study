package com.shang.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * 可见性3-1：在没有同步的情况下共享变量
 * Created by Think on 2017/7/14.
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReadThread extends  Thread{
        public void run(){
            while(!ready){
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadThread().start();
        TimeUnit.SECONDS.sleep(50);
        number = 42;
        ready = true;
    }
}
