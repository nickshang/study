package com.shang.chapter12;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * SemaphoreBoundedBufferTest测试
 * Created by Think on 2017/7/24.
 */

public class SemaphoreBoundedBufferTest {

    private static final long LOCKUP_DETECT_TIMEOUT = 10000;
    private static final int CAPACITY = 10000;
    private static final int THRESHOLD = 10000;

    /**
     * 常规方法测试
     */
    @Test
    public void testIsEmptyWhenConstructed() {
        SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<>(10);
        assertThat("availableItems为空",true,is(bb.isEmpty()) );
        assertThat("availableSpaces是满",false,is(bb.isFull()) );
    }

    /**
     * 常规方法测试
     * @throws InterruptedException
     */
    @Test
    public void testIsFullAfterPuts() throws  InterruptedException {
        SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<>(10);
        for(int i = 0; i < 10 ; i++){
            bb.put(i);
        }

        assertThat(false,is(bb.isEmpty()));
        assertThat(true,is(bb.isFull()));
    }

    /**
     * 测试阻塞行为以及对中断的响应
     */
    @Test
    public void testTakeBlockWhenEmpty() {
        final SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<>(10);
        Thread taker = new Thread() {
          public void run(){
              try{
                  int unuserd = bb.take();
                  fail("错误：从本该空的队列取出数据!");
              }catch (InterruptedException e){
                   throw new RuntimeException(e);
              }
          }
        };


        try{
            taker.start();
            System.out.println("##start:" + new Date().toString());
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            System.out.println("##awakened:" + new Date().toString());
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);                  // 主线程加入到taker线程 -> 在主线程在加入到taker线程1000毫秒后返回(如果take已经中断，将立即返回) -> 验证taker线程是否成功被中断

            assertThat(false,is(taker.isAlive()));       // 判断taker线程是否结束-》taker.isAlive()返回false表示taker结束
            System.out.println("##end:" + new Date().toString());
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}