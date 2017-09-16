package java7.classbytecode;


import java7.classbytecode.ThreadPoolManager.CancelProxy;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledFuture;

public class ThreadPoolMain {

  // 定期读取队列内容
  private ThreadPoolManager manager;


  // 利用反射进行任务取消
  private void cancelUsingReflection(ScheduledFuture<?> hndl) {
    Method meth = manager.makeReflective();

    try {
      System.out.println("cancel With Reflection");
      meth.invoke(hndl);
    } catch (IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  // 利用代理进行任务取消
  private void cancelUsingProxy(ScheduledFuture<?> hndl) {
    CancelProxy proxy = manager.makeProxy();

    System.out.println("cancel With Proxy");
    proxy.invoke(manager, hndl);
  }

  // 利用MethodHandle任务取消 // jdk7新特性
  private void cancelUsingMH(ScheduledFuture<?> hndl) {
    MethodHandle mh = manager.makeMh();

    try {
      System.out.println("cancel With Method Handle");
      mh.invokeExact(manager, hndl);
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  private void run() {

    // 阻塞队列
    BlockingQueue<WorkUnit<String>> lbq = new LinkedBlockingQueue<>();

    // 初始化队列内容
    for(int i = 0; i < 5; i++){
      WorkUnit<String> workUnit = new WorkUnit<>(String.valueOf(i));
      lbq.offer(workUnit);
    }

    // 定期读取队列内容
    manager = new ThreadPoolManager(lbq);

    // 任务线程
    final QueueReaderTask msgReader = new QueueReaderTask(100) {
      @Override
      public void doAction(String msg_) {
        if (msg_ != null)
          System.out.println("Msg recvd: " + msg_);
      }
    };

    // 读取开始
    ScheduledFuture<?> hndl = manager.run(msgReader);                   // 取消时需要

//    cancelUsingMH(hndl);
     cancelUsingProxy(hndl);
//     cancelUsingReflection(hndl);

    // 初始化队列内容
    for(int i = 0; i < 500; i++){
      WorkUnit<String> workUnit = new WorkUnit<>(String.valueOf(i));
      lbq.offer(workUnit);
    }


  }

  public static void main(String[] args) {
    ThreadPoolMain main = new ThreadPoolMain();
    main.run();

    System.out.println("----主程序结束--------");
  }
}
