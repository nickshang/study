package com.shang.queue;

import java.util.concurrent.*;

/**
 * ConcurrentLinkedQueue 出列性能测试
 * concurrentLinkedQueue.size() 遍历整个链表计算元素较慢
 * concurrentLinkedQueue.isEmpty() 只需要判断头指针节点item域是否为空即可，速度较快
 * @author NICK
 * @create 2018-01-26
 **/
public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        // 入列
        offer(concurrentLinkedQueue);

        long start = System.currentTimeMillis();

        // 出列
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(new TestThread(concurrentLinkedQueue, countDownLatch));
        executorService.submit(new TestThread(concurrentLinkedQueue, countDownLatch));

        countDownLatch.await();

        // 统计时间
        long end = System.currentTimeMillis();
        System.out.println("millis:" + (end - start));

        executorService.shutdown();
    }

    public static void offer(ConcurrentLinkedQueue<String> concurrentLinkedQueue ){
        for (int i = 0; i < 10000 ; i++) {
            concurrentLinkedQueue.offer("1");
        }
    }


    static class TestThread implements Runnable{

        private  ConcurrentLinkedQueue<String> concurrentLinkedQueue;

        private  CountDownLatch countDownLatch;

        TestThread(final ConcurrentLinkedQueue<String> concurrentLinkedQueue,final CountDownLatch countDownLatch){
            this.concurrentLinkedQueue = concurrentLinkedQueue;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            while (!concurrentLinkedQueue.isEmpty()) {
                System.out.println(concurrentLinkedQueue.poll());
            }
            this.countDownLatch.countDown();
        }
    }
}
