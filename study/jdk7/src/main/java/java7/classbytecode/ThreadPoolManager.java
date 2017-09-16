package java7.classbytecode;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * 定期读取的例子：msgReader对象被安排一个poll()一个队列，从队列中WorkUnti对象你去的工作项，然后输出。
 * ScheduledExecutorService每10毫秒唤醒一次，让他尝试poll一个队列.
 * 如果返回null （当前队列为空），则什么也不会发生
 * 如果收入一个工作单元，则线程会输出该工作单元的内容
 */
public class ThreadPoolManager {



  private final ScheduledExecutorService stpe = Executors
      .newScheduledThreadPool(2);                                                                 //  执行者的工厂方法

  private final BlockingQueue<WorkUnit<String>> lbq;                                                          //  工作队列

  public ThreadPoolManager(BlockingQueue<WorkUnit<String>> lbq_) {
    lbq = lbq_;
  }                              //  初始化工作队列

  public ScheduledFuture<?> run(QueueReaderTask msgReader) {

    msgReader.setQueue(lbq);                                                                                  //  设置线程任务读取的队列
    return stpe.scheduleAtFixedRate(msgReader, 10, 10, TimeUnit.MILLISECONDS);               //   ScheduledExecutorService每10毫秒唤醒一次，让他尝试poll一个队列.
  }

  /**
   * 取消任务
   * @param hndl
   */
  private void cancel(final ScheduledFuture<?> hndl) {
    stpe.schedule(new Runnable() {
      public void run() {
        hndl.cancel(true);
      }
    }, 10, TimeUnit.MILLISECONDS);
  }

  public Method makeReflective() {
    Method meth = null;

    try {
      Class<?>[] argTypes = new Class[] { ScheduledFuture.class };
      meth = ThreadPoolManager.class.getDeclaredMethod("cancel", argTypes);
      meth.setAccessible(true);
    } catch (IllegalArgumentException | NoSuchMethodException
        | SecurityException e) {
      e.printStackTrace();
    }

    return meth;
  }

  /**
   * 代理类
   */
  public static class CancelProxy {
    private CancelProxy() {
    }

    public void invoke(ThreadPoolManager mae_, ScheduledFuture<?> hndl_) {
      mae_.cancel(hndl_);
    }
  }


  public CancelProxy makeProxy() {
    return new CancelProxy();
  }

  public MethodHandle makeMh() {
    MethodHandle mh;
    MethodType desc = MethodType.methodType(void.class, ScheduledFuture.class);

    try {
      mh = MethodHandles.lookup().findVirtual(ThreadPoolManager.class,
          "cancel", desc);
    } catch (NoSuchMethodException | IllegalAccessException e) {
      throw (AssertionError) new AssertionError().initCause(e);
    }

    return mh;
  }
}