package thinkinjava.chapter15_generator.c2;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @类描述：实现一个泛型容器
 * 假设我们需要一个特定的类型对象的列表
 * 每次调用其上的select()方法
 * 他就可以随机选择一个元素我们
 * 如果我们希望以此构建一个可以应用与各种类型的对
 * 
 * 的工具，就需要用到泛型
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月24日 下午3:40:45
 * @修改人：NICK
 * @修改时间：2016年4月24日 下午3:40:45
 * @修改备注：
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class RandomList<T> {
	
	private ArrayList<T> storage = new ArrayList<T>();
	
	private Random rand = new Random(47);
	
	public void add(T t){
		storage.add(t);
	}
	
	public T select(){
		return storage.get(rand.nextInt(storage.size()));
	}
	
	public static void main(String[] args) {
		RandomList<String> t1 = new RandomList<String>();
		
		for(String s :  "c c++  c# java go scala".split(" ") ){
			t1.add(s);
		}
		
		for(int i = 0; i < 10; i++){
			System.out.print( t1.select() + " ");
		}
		System.out.print("\n");
		
		RandomList<Double> t2= new RandomList<Double>();

		Double[] d = {11.1, 22.3, 33.4, 44.5, 55.6};
		for(Double s : d){
			t2.add(s);
		}
		
		for(int i = 0; i < 4; i++){
			System.out.print( t2.select() + " ");
		}
		
		System.out.print("\n");

		RandomList<Integer> t3= new RandomList<Integer>();

		int[] in = {11, 22, 33, 44, 55};
		for(int s : in){
			t3.add(s);
		}
		
		for(int i = 0; i < 4; i++){
			System.out.print( t3.select() + " ");
		}
		
	}
}
