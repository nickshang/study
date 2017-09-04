package com.shang.chapter3;

import com.shang.common.NotThreadSafe;

/**
 * 可见性 程序清单3-2 非线程安全的可变整数类
 * Created by Think on 2017/7/15.
 */
@NotThreadSafe
public class MutableInteger {
    private int value;


    public synchronized int  getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
