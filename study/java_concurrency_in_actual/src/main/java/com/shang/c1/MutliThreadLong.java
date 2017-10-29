package com.shang.c1;


/**
 *  测试Long对象的原子性
 *  针对32位虚拟机可能出现读取到不合理值
 *
 * Created by Think on 2017/10/25.
 */
public class MutliThreadLong {
    public static long t = 0 ;


    public static class ChangT implements Runnable{

        private long t;
        public ChangT(long to){
            this.t = to;
        }

        @Override
        public void run() {
            while(true){
                MutliThreadLong.t = this.t;
                Thread.yield();
            }
        }
    }


    public static class ReadT implements Runnable{

        @Override
        public void run() {
            while(true){
                long temp = MutliThreadLong.t;
                // 不等于变化值打印结果
                if(temp != 999
                        &&  temp != -799
                        &&  temp != -897
                        &&  temp != 333){
                    System.out.println(temp);
                    Thread.yield();
                }
            }
        }
    }

    public static void main(String[] args) {
            new Thread( new ChangT(999)).start();
            new Thread( new ChangT(-799)).start();
            new Thread( new ChangT(-897)).start();
            new Thread( new ChangT(333)).start();
            new Thread(new ReadT()).start();
    }
}
