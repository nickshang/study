package java7.forkjoin;

import java.util.concurrent.*;

/**
 * Fork/Join测试类
 * @author NICK
 * @create 2018-01-28
 **/


public class CountTask extends RecursiveTask<Integer> {

    // 任务阀值
    private static final int THRESHOLD = 2;

    private int start;

    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        int sum = 0;

        // 根据阀值判断
        boolean canCompute = (start - end) <= 2;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        }else {
            int middle = (start + end) / 2;
            RecursiveTask<Integer> leftTask = new CountTask(start,middle);
            RecursiveTask<Integer> rightTask = new CountTask(middle+1, end);
            leftTask.fork();
            rightTask.fork();

            int leftSum = leftTask.join();
            int rightSum = leftTask.join();

            sum += leftSum + rightSum;

        }
        return sum;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        CountTask countTask = new CountTask(1,8);
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(countTask);

        int resutl = forkJoinTask.get(100, TimeUnit.SECONDS);
        System.out.println(resutl);

        int i = 1;

        System.out.println("1^i:" + (1^i));

        System.out.println(1 << 5);
        System.out.println(-19 >> 10);

        System.out.println(Integer.toBinaryString(-19));
        System.out.println(Integer.toBinaryString(-19 >> 10 ));

        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-1 >>> 1));

        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
    }
}
