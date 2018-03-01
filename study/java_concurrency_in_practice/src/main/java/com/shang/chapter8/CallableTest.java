package com.shang.chapter8;

import java.util.concurrent.*;

/**
 * 测试利用Callable实现类，提交线程返回结果
 * @author NICK
 * @create 2018-01-27
 **/
public class CallableTest implements java.util.concurrent.Callable {

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        return "success";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<String> futrue = executorService.submit(new CallableTest());

        String result = futrue.get();

        System.out.println("result:" + result);

        executorService.shutdown();
    }
}
