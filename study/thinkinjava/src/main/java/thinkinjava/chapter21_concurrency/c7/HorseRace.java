package thinkinjava.chapter21_concurrency.c7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：赛马仿真
 * 学习使用CyclicBarrier
 * @author NICK
 *
 */
public class HorseRace {
	
	//间隔数量
	static final int FINISH_LINE = 75;
	
	//马儿集合
	private List<Horse> horses = new ArrayList<Horse>();
	
	//线程池
	private final ExecutorService exec = Executors.newCachedThreadPool();
	
	//循环公共屏障-同步辅助类
	private final CyclicBarrier barrier;
	
	public HorseRace(int nHorses, final int pause){
		
			//CyclicBarrier 支持一个可选的 Runnable 命令，在一组线程中的最后一个线程到达之后（但在释放所有线程之前），该命令只在每个屏障点运行一次。
			//若在继续所有参与线程之前更新共享状态，此屏障操作 很有用。
			barrier = new CyclicBarrier(nHorses, new Runnable(){

				@Override
				public void run() {
					StringBuilder s = new StringBuilder();
					for(int i = 0; i < FINISH_LINE; i++){
						s.append("=");
					}
					System.out.println(s);
					
					for (Horse horse : horses )
						System.out.println(horse.tracks());
					
					for (Horse horse : horses ){
						if (horse.getStrides() >= FINISH_LINE ){
							System.out.print( horse + "won!" );
							exec.shutdownNow();
							return;
						}
					}
					
					try {
						TimeUnit.MICROSECONDS.sleep(pause);
					}catch (InterruptedException e){
						System.out.print( "barrier-action sleep interrupted" );
					}
				}
		});
		
		for(int i = 0; i < nHorses; i++){
			Horse horse = new Horse(barrier);
			horses.add( horse );
			exec.execute(horse);
		}
	}
	
	
	public static void main(String[] args){
		
		//马儿数量
		int nHorses = 7;
		
		//停顿次数
		int pause = 200;
		
		if (args.length > 0) {
			int n = new Integer(args[0]);
			nHorses = n > 0 ? n : nHorses;
		}
		
		if(args.length > 1 ){
			int p = new Integer(args[1]);
			pause = p > -1 ? p : pause;
		}
		
		
		//开始赛马游戏
		new HorseRace(nHorses, pause);
	}
}

/**
 * 功能描述：马
 * @author NICK
 *
 */
class Horse implements Runnable{
	
	//编号记录数
	private static int count = 0;
	
	//编号
	private final int id = count++;
	
	//循环阻塞队列
	private final CyclicBarrier barrier;
	
	//步幅，进度
	private int strides = 0;
	
	//随机类
	Random rand = new Random();
	
	/**
	 * 功能描述：获取进度
	 * @return
	 */
	public synchronized  int getStrides() {
		return strides;
	}
	
	/**
	 * 功能描述：新建一匹马实例
	 * @param b
	 */
	public Horse(CyclicBarrier b){
		barrier = b;
	}
	
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				synchronized(this){
					strides += rand.nextInt(3);
				}
				barrier.await();
			}
		}catch(InterruptedException e){
			throw new RuntimeException();
		} catch (BrokenBarrierException e) {
			throw new RuntimeException();
		}
	}
	
	//重新toString()
	public String toString(){ return "Horse" + id + " "; }
	
	/**
	 * 通知
	 */
	public String tracks(){
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < getStrides(); i++){
			s.append("*");
		}
		s.append(id);
		return s.toString();
	}
}
