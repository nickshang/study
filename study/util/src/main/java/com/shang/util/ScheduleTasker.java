package com.shang.util;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述: 定时执行线程池
 * @author Nick
 *
 */
public class ScheduleTasker {

	// 默认线程池大小
	private int corePoolSize = 40;
	ScheduledThreadPoolExecutor scheduler;

	// 无参数构造定时启动线程
	public ScheduleTasker() {
		
		scheduler = new ScheduledThreadPoolExecutor(corePoolSize);
		
		// 设置有关在此执行程序已 shutdown 的情况下是否继续执行现有定期任务的策略。
		scheduler.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
		
		//设置有关在此执行程序已 shutdown 的情况下是否继续执行现有延迟任务的策略。
		scheduler.setExecuteExistingDelayedTasksAfterShutdownPolicy(true);
		
	}

	// 代参数指定线程池大小 构造定时启动线程
	public ScheduleTasker(int quantity) {
		corePoolSize = quantity;
		scheduler = new ScheduledThreadPoolExecutor(corePoolSize);
		
		// 设置有关在此执行程序已 shutdown 的情况下是否继续执行现有定期任务的策略。
		scheduler.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
		
		//设置有关在此执行程序已 shutdown 的情况下是否继续执行现有延迟任务的策略。
		scheduler.setExecuteExistingDelayedTasksAfterShutdownPolicy(true);
	}

	/**
	 * 功能描述:指定线程 运行间隔时间
	 * 
	 * @param event
	 *            运行线程
	 * @param initialDelay
	 *            首次执行的延迟时间
	 * @param period
	 *            连续执行之间的周期
	 */
	public void schedule(Runnable event, long initialDelay, long period) {
		scheduler.scheduleAtFixedRate(event, initialDelay, period,
				TimeUnit.SECONDS);
	}

	/**
	 * 功能描述:关闭线程池
	 */
	public void shutdown() {
		scheduler.shutdown();
	}
	
	
	/**
	 * 功能描述:查看线程池是否存活
	 * @return
	 */
	public boolean  isLive() {
		if( scheduler == null )
		{
			return false;
		}else
		{
			return scheduler.isShutdown();
		}
	}
	

	/**
	 * 功能描述:计算时间差
	 * @return 时间
	 */
	public static long Time()
	{
	    /*** 定制每日00：10：00 时间 ***/ 
	   Calendar calendar = Calendar.getInstance(TimeZone.getDefault());  
       int year = calendar.get(Calendar.YEAR);  
       int month = calendar.get(Calendar.MONTH);  
       int day = calendar.get(Calendar.DAY_OF_MONTH);
       calendar.set(year, month, day, 24, 10, 00);  
       
       /*** 现在时间 ***/ 
       Calendar calendar2 = Calendar.getInstance(TimeZone.getDefault());  
       
       /*** 首次执行的延迟时间 距离00：10：00的时间秒 ***/ 
       long initialDelay = (calendar.getTimeInMillis() -calendar2.getTimeInMillis())/1000 ;
       
       return initialDelay;
	}
}
