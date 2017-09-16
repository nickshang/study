package java7.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 经典死锁例子：
 *
 * -----线程1------------线程2------
 *       A(获取对象锁a)
 *
 *                       B(获取对象锁b)
 *
 *       B(需要获取对象b完成完成工作,由于线程2未完成工作，占用对象锁b，导致死锁)
 *
 *                       A (需要获取对象a完成完成工作)
 *
 * Think on 2016/6/25.
 */
public class DeathThread {

    /**
     * 线程1
     */
    public static class T1 implements Runnable {
        Ttest t;

        public T1(Ttest t) {
            this.t = t;
        }

        @Override
        public void run() {
            t.setA();
            t.setB();
        }
    }



    /**
     * 线程2
     */
    public static class T2 implements Runnable {
        Ttest t;

        public T2(Ttest t) {
            this.t = t;
        }

        @Override
        public void run() {
            t.setB();
            t.setA();
        }
    }

    public static void main(String[] args) {
        Ttest t = new Ttest();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new T1(t));
        exec.execute(new T2(t));
        exec.shutdown();
    }

}

class Ttest {

    ReentrantLock lock1 = new ReentrantLock();

    ReentrantLock lock2 = new ReentrantLock();

    /**
     * 方法A
     */
    public void setA() {
        try {
            lock1.lock();               // 获取可重入门锁1
            TimeUnit.SECONDS.sleep(3);  // 睡眠

            lock2.lock();               // 获取可重入门锁2

            System.out.println("A");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock2.unlock();
            lock1.unlock();
        }
    }

    /**
     * 方法B
     */
    public void setB() {
        try {
            lock2.lock();               // 获取可重入门锁2
            TimeUnit.SECONDS.sleep(3);  // 睡眠

            lock1.lock();               // 获取可重入门锁1
            System.out.println("B");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock2.unlock();
            lock1.unlock();
        }
    }

}

