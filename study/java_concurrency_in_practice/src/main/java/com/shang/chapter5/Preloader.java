package com.shang.chapter5;

import java.util.concurrent.*;

/**
 * 同步工具类-FutureTask（闭锁）
 * Preloader
 *
 * Using FutureTask to preload data that is needed later
 * 使用FutureTask提前加载数据，将在稍后使用。 </p>
 *
 * Preloader创建了一个FutureTask，其中包含从数据库加载产品信息的任务，以及一个执行运算的线程，
 * 由于构造函数或静态初始化方法由启动线程并不是一种好方法，因此提供了一个start方法来启动线程。
 * 当程序随后ProductInfo时，可以调用get方法，如果数据已经加载，那么将返回这些数据，否则将等待加载完成后再返回。</p>
 *
 * Callable表示的任务可以抛出受检查的或者未检查的异常，并且任务代码都可以抛出一个Error。
 * 无任何代码抛出什么异常，都会封装到一个ExceutionException中，并在Future.get中被重新抛出。
 * 这将使调用get的代码变得复杂，因为他不仅需要处理可能出现的ExceutionException(以及为检查CancellationException)，
 * 而且由于ExceutionException是作为Throwable类返回的，因此出来并不容易。</p>
 *
 * 在Proloader中，当get方法抛出ExceutionException时，可能是以下三种情况之一：Callable抛出的受检查异常，
 * RuntimeExcepton，以及Error。我们必须对每种情况进行单独处理，
 * 单我们将使用5-13launderThrowable辅助方法来封装一个复制的异常处理逻辑。在调用landerThrowable之前，
 * Prelaoder会首先检查已知的受检查异常，并重新抛出他们。剩下的为检查异常，
 * Preloader将调用lanuderThrowable并抛出结果。如果Throwable传递给launderThrowable的是一个Error，
 * 那么launderThrowable将直接再次抛出它；如果不是RuntimeException，
 * 那么将抛出一个IlllegaleSateExceptiono表示这是一个逻辑错误。剩下的RuntimeException，
 * lanuderThrowable将把他们返回给调用者，而调用者通常是重写抛出他们的。</p>
 *
 * @author Brian Goetz and Tim Peierls
 */

public class Preloader {
    ProductInfo loadProductInfo() throws DataLoadException {
        return null;
    }

    private final FutureTask<ProductInfo> future =
        new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
            public ProductInfo call() throws DataLoadException {
                return loadProductInfo();
            }
        });
    private final Thread thread = new Thread(future);

    public void start() { thread.start(); }

    public ProductInfo get()
            throws DataLoadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException)
                throw (DataLoadException) cause;
            else
                throw LaunderThrowable.launderThrowable(cause);
        }
    }

    interface ProductInfo {
    }
}

class DataLoadException extends Exception { }
