package com.shang.chapter7;

import java.math.BigInteger;
import java.util.concurrent.*;

/**
 * BrokenPrimeProducer
 * <p/>
 * Unreliable cancellation that can leave producers stuck in a blocking operation
 * 不可靠的取消操作将生产者置于阻塞的操作中（不要这样做）
 *
 * 当生产者的速度超过了消费者的处理速度，队列将被填满，put方法也会阻塞。
 * 当生产者在put方法中阻塞，如果消费者希望取消生产者任务，那么将发生什么情况?
 * 他可以调用cancel方法来设置cancelled标准，但是此时生产者缺永远不会检查这个标志。因为他无法从阻塞的put方法返回过来。
 *
 * @author Brian Goetz and Tim Peierls
 */
class BrokenPrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!cancelled)
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException consumed) {
        }
    }

    public void cancel() {
        cancelled = true;
    }
}

