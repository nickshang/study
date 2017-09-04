package com.shang.chapter5;

import java.util.*;
import java.util.concurrent.*;

/**
 * BoundedHashSet
 * <p/>
 * Using Semaphore to bound a collection
 * 使用Semaphore为容器设置边界</p>
 *
 * 信号量的计数值会初始为容器容量的最大值。
 * add操作在想低层容器中添加了一个元素之前，首先要获得一个许可。
 * 如果add操作没有添加任何元素，那么会立刻释许可。同样,remove操作释放一个许可，使更多的元素能够添加到容器中。
 * 低层的Set实现并不知道关于边界的任何消息，这是有BoundedHashSet来处理的。</p>
 *
 * @author Brian Goetz and Tim Peierls
 */
public class BoundedHashSet <T> {
    private final Set<T> set;
    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded)
                sem.release();
        }
    }

    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved)
            sem.release();
        return wasRemoved;
    }
}
