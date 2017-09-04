package com.shang.chapter14;

import net.jcip.annotations.*;

/**
 * ThreadGate
 * <p/>
 * Recloseable gate using wait and notifyAll
 * 使用wait和notifyAll来实现可重新关闭的阀门
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class ThreadGate {
    // 条件谓语：CONDITION-PREDICATE: opened-since(n) (isOpen || generation>n)
    @GuardedBy("this") private boolean isOpen;
    @GuardedBy("this") private int generation;

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        ++generation;
        isOpen = true;
        notifyAll();
    }

    // 阻塞直到：BLOCKS-UNTIL: opened-since(generation on entry)
    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = generation;
        while (!isOpen && arrivalGeneration == generation)
            wait();
    }
}
