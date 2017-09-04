package java7.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �����������ӣ�
 *
 * -----�߳�1------------�߳�2------
 *       A(��ȡ������a)
 *
 *                       B(��ȡ������b)
 *
 *       B(��Ҫ��ȡ����b�����ɹ���,�����߳�2δ��ɹ�����ռ�ö�����b����������)
 *
 *                       A (��Ҫ��ȡ����a�����ɹ���)
 *
 * Think on 2016/6/25.
 */
public class DeathThread {

    /**
     * �߳�1
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
     * �߳�2
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
     * ����A
     */
    public void setA() {
        try {
            lock1.lock();               // ��ȡ����������1
            TimeUnit.SECONDS.sleep(3);  // ˯��

            lock2.lock();               // ��ȡ����������2

            System.out.println("A");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock2.unlock();
            lock1.unlock();
        }
    }

    /**
     * ����B
     */
    public void setB() {
        try {
            lock2.lock();               // ��ȡ����������2
            TimeUnit.SECONDS.sleep(3);  // ˯��

            lock1.lock();               // ��ȡ����������1
            System.out.println("B");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock2.unlock();
            lock1.unlock();
        }
    }

}

