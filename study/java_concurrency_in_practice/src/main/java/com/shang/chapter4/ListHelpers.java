package com.shang.chapter4;

import java.util.*;

import net.jcip.annotations.*;

/**
 * ListHelder
 * <p/>
 * Examples of thread-safe and non-thread-safe implementations of
 * put-if-absent helper methods for List
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
class BadListHelper <E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    // 错误的锁
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent)
            list.add(x);
        return absent;
    }
}

/**
 * 要想这个方法正确执行，必须使List在实现客户端加锁或者外部加锁时使用同一个锁。
 * 客户端加锁是指，对于某个对象X的客户端代码，使用X本身用于保护器状态的说来保护这段客户代码。
 * 要使用客户端加锁，你必须知道对象X使用的是那个一个锁。
 * @param <E>
 */
@ThreadSafe
class GoodListHelper <E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public boolean putIfAbsent(E x) {
        //
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent)
                list.add(x);
            return absent;
        }
    }
}
