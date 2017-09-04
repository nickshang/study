package com.shang.chapter3;

import com.shang.common.ThreadSafe;

/**
 * 可见性 程序清单3-3 线程安全的可变整数类
 * Created by Think on 2017/7/15.
 */
@ThreadSafe
public class SynchronizedInteger {
    private int value;

    public synchronized int  getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }
}
