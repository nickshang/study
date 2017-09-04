package com.shang.chapter12;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import junit.framework.TestCase;

/**
 * PutTakeTest
 * <p/>
 * Producer-consumer test program for BoundedBuffer
 * 测试SemaphoreBoundedBuffer的生产者-消费者程序
 *
 * PutTakeTest启动了N个生产者线程来生成元素并吧他们插入到队列，同时还启动N个消费者线程队列中取出元素。
 * 当元素进出队列时，每个线程会更新对象对这些元素计算得到的效验和，每个线程都拥有一个效验和，
 * 并在结束后，从而在测试缓存就不会进入更多的同步或竞争。
 *
 * @author Brian Goetz and Tim Peierls
 */
//public class PutTakeTest extends TestCase {
public class PutTakeTest  {
    protected static final ExecutorService pool = Executors.newCachedThreadPool();
    protected CyclicBarrier barrier;
    protected final SemaphoreBoundedBuffer<Integer> bb;
    protected final int nTrials, nPairs;
    protected final AtomicInteger putSum = new AtomicInteger(0);
    protected final AtomicInteger takeSum = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        new PutTakeTest(10, 10, 100000).test(); // 示例参数
        pool.shutdown();
    }

    /**
     * 构造函数
     * @param capacity 容量
     * @param npairs 线程数量
     * @param ntrials 对SemaphoreBoundedBuffer操作次数
     */
    public PutTakeTest(int capacity, int npairs, int ntrials) {
        this.bb = new SemaphoreBoundedBuffer<Integer>(capacity);
        this.nTrials = ntrials;
        this.nPairs = npairs;
        this.barrier = new CyclicBarrier(npairs * 2 + 1);
    }

    void test() {
        try {
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new Producer());         // 提交线程任务并在第一处：barrier.await()等待将现场列入队列
                pool.execute(new Consumer());         // 提交线程任务并在第一处：barrier.await()等待将现场列入队列
            }
            barrier.await();                         // wait for all threads to be ready 等待所有线程准备完成  -> 将本线程也列队列 -> 队列将达到21次 -> 一次将释放所有线程
            barrier.await();                         // wait for all threads to finish   等待所有线程完成      -> 所有线程释放后将程序的第二处等待（列入20个线程队列） -> 队列达到21次 ->一次释放（所有的结果将都放到：putSum takeSum）

            System.out.println("###putSum.get():" + putSum.get() );
            System.out.println("###takeSum.get():" + takeSum.get() );
            System.out.println("###结果:" + (putSum.get() == takeSum.get() ) );
//            assertEquals(putSum.get(), takeSum.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }

    class Producer implements Runnable {
        public void run() {
            try {
                int seed = (this.hashCode() ^ (int) System.nanoTime());
                int sum = 0;
                barrier.await();
//                System.out.println( "############Producer start:" + barrier.getParties() );
                for (int i = nTrials; i > 0; --i) {
                    bb.put(seed);
                    sum += seed;
                    seed = xorShift(seed);
                }
                putSum.getAndAdd(sum);
                barrier.await();
//                System.out.println( "############Producer　end:" + barrier.getParties() );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    class Consumer implements Runnable {
        public void run() {
            try {
                barrier.await();
//                System.out.println( "############Consumer start:" + barrier.getParties() );
                int sum = 0;
                for (int i = nTrials; i > 0; --i) {
                    sum += bb.take();
                }
                takeSum.getAndAdd(sum);
                barrier.await();
//                System.out.println( "############Consumer end:" + barrier.getParties() );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
