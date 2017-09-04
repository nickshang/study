package com.shang.chapter5;

import java.util.concurrent.CountDownLatch;

/**
 * 在计时测试中使用使用CountDownLatch来启动和停止线程
 * Created by Think on 2017/7/27.
 */
public class TestHarness {

    public long timeTasks(int nThreads,final Runnable task) throws InterruptedException{
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++){
            Thread t = new Thread() {
                public void run(){
                    try{
                        startGate.await();
                        try{
                            task.run();
                        }finally {
                            endGate.countDown();
                        }
                    }catch (Exception e){
                        throw  new RuntimeException(e);
                    }
                }
            };
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();                              // 释放锁，所有线程可以运行
        endGate.await();                                    // 等待所有线程运行完成
        long end = System.nanoTime();

        return end  - start;
    }
}
