package thinkinjava.chapter21_concurrency.c2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 功能描述：使用Callable<V>实现线程返回值
 * 
 * @author NICK
 * @time 2015-10-10
 */
public class TaskWritResult implements Callable<String> {

	private int id;

	public TaskWritResult(int id) {
		this.id = id;
	}

	/**
	 * 功能描述：获取返回值
	 * 
	 * @throws Exception
	 *             if unable to compute a result
	 */
	public String call() throws Exception {
		return "result of TaskWithResutl " + id;

		// 随机产生一个数字，设置为当前线程的睡眠时间,将当前现场设置为阻塞。用于测试get()方法，isDone()方法的区别
		// int s = (int)(Math.random() );
		// Thread.currentThread().sleep( s );
		// return "Thread id : " + id + ", time:" + s ;
	}

	public static void main(String[] args) {

		// 1、线程执行管理器
		java.util.concurrent.ExecutorService excutor = java.util.concurrent.Executors
				.newCachedThreadPool();

		// 2、向线程执行管理器添加现场
		List<Future<String>> list = new ArrayList<Future<String>>();
		for (int i = 0; i < 10000; i++)
			list.add(excutor.submit(new TaskWritResult(i)));

		// 3、查看返回的结果
		for (Future<String> b : list) {
			try {
				// 1、直接调用get(),执行将阻塞，直到结果准备就绪
				System.out.println(b.get());

				// 2、先调用isDone(),判断任务是否完成，在调动get（）获取返回结果
				if (b.isDone())
					System.out.println(b.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {
				// 3、启动一次顺序关闭，执行以前提交的任务，但不接受新任务
				excutor.shutdown();
			}
		}
	}
}
