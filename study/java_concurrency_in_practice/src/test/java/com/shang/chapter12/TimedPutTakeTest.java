package com.shang.chapter12;

import java.util.concurrent.*;

/**
 * TimedPutTakeTest
 * <p/>
 * Testing with a barrier-based timer
 * 采用基于栅栏的定时器进行测试
 *
 * @author Brian Goetz and Tim Peierls
 */
public class TimedPutTakeTest extends PutTakeTest {
    private BarrierTimer timer = new BarrierTimer();

    public TimedPutTakeTest(int cap, int pairs, int trials) {
        super(cap, pairs, trials);
        barrier = new CyclicBarrier(nPairs * 2 + 1, timer);
    }

    public void test() {
        try {
            timer.clear();
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new PutTakeTest.Producer());
                pool.execute(new PutTakeTest.Consumer());
            }
            barrier.await();
            barrier.await();
            long nsPerItem = timer.getTime() / (nPairs * (long) nTrials);                       // 执行时间 /  (线程数 * 操作次数)
            System.out.print("Throughput: " + nsPerItem + " ns/item");

//            System.out.println( "#####结果:" + (putSum.get() == takeSum.get() ));
//            assertEquals(putSum.get(), takeSum.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        int tpt = 100000;                                                                       // trials per thread 每个线程测试的次数
        for (int cap = 1; cap <= 1000; cap *= 10) {
            System.out.println("Capacity: " + cap);
            for (int pairs = 1; pairs <= 128; pairs *= 2) {
                TimedPutTakeTest t = new TimedPutTakeTest(cap, pairs, tpt);
                System.out.print("Pairs: " + pairs + "\t");
                t.test();
                System.out.print("\t");
                Thread.sleep(1000);
                t.test();
                System.out.println();
                Thread.sleep(1000);
            }
        }
        PutTakeTest.pool.shutdown();
    }
}
