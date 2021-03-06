package com.shang.chapter8;

import java.util.concurrent.*;

/**
 * MyThreadFactory
 * <p/>
 * Custom thread factory
 * 定义线程名称
 *
 * @author Brian Goetz and Tim Peierls
 */
public class MyThreadFactory implements ThreadFactory {
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return new MyAppThread(runnable, poolName);
    }
}
