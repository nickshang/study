package com.shang.chapter15;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.*;

import net.jcip.annotations.*;

/**
 * LinkedQueue
 * <p/>
 * Insertion in the Michael-Scott nonblocking queue algorithm
 * 非阻塞算法中的插入算法：利用原子类（AtomicReference）和非阻塞算法
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class LinkedQueue<E> {

    private static class Node<E> {

        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<Node<E>>(next);
        }

    }

    // 哑节点/
    private final Node<E> dummy = new Node<E>(null, null);

    // 头节点
    private final AtomicReference<Node<E>> head
            = new AtomicReference<Node<E>>(dummy);

    // 尾部节点
    private final AtomicReference<Node<E>> tail
            = new AtomicReference<Node<E>>(dummy);

    public boolean put(E item) {
        Node<E> newNode = new Node<E>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if (curTail == tail.get()) {
                if (tailNext != null) {
                    // 队列处于中间状态，推进尾节点 Queue in intermediate state, advance tail
                    tail.compareAndSet(curTail, tailNext);
                } else {
                    // 处于稳定状态，尝试插入新节点  In quiescent state, try inserting new node
                    if (curTail.next.compareAndSet(null, newNode)) {
                        // 插入操作成功，尝试推进尾节点  Insertion succeeded, try advancing tail
                        tail.compareAndSet(curTail, newNode);
                        return true;
                    }
                }
            }
        }
    }


    public static void main(String[] args) {

        String s =  "";
        HashMap map = null;

        int i = 49;
        System.out.println(i%50);
//        if( i%50 > 1 ) {
//            stmt.executeBatch();
//        }
    }
}
