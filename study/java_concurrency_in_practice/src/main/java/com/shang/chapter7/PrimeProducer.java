package com.shang.chapter7;

import java.math.BigInteger;
import java.util.concurrent.*;

/**
 * PrimeProducer
 * <p/>
 *
 * Using interruption for cancellation
 * 通过中断来来取消任务
 *
 * 调用interruption并不意味这立即停止目标线程正在进行的工作，而只是传递了请求中中断的消息。
 *
 * 对中断的正确的理解是：他不会真正的中一个正在运行的现场，而只是发出中断的请求，然后由线程下一个合适的时刻来中断自己。
 *
 * 通常，中断是实现取消的最合适的方式。
 *
 * @author Brian Goetz and Tim Peierls
 */
public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted())
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException consumed) {
            /* Allow thread to exit */
        }
    }

    public void cancel() {
        interrupt();
    }
}
