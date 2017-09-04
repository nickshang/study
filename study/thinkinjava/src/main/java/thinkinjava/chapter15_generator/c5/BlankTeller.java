package thinkinjava.chapter15_generator.c5;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import thinkinjava.net.mindview.util.Generator;

/**
 * 
 * @类描述：泛型在匿名内部类中的使用 （构建服务员为依次为不同的客户进行服务）
 * 
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月27日 下午10:25:18
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class BlankTeller {
	public static void server(Teller t, Customer c){
		System.out.println( t + " server " + c);
	}
	public static void main(String[] args) {
		Random random = new Random(47);
		Queue<Customer> cs = new LinkedList<>(); 
		Generators.fill(cs,Customer.generator(), 30);
		
		List<Teller> tr = new LinkedList<>(); 
		Generators.fill(tr,Teller.generator(), 4);
		
		for( int i = 0; i < cs.size(); i++ ){
			server( tr.get( random.nextInt(tr.size())) ,  cs.poll());
			i--;
		}
		
	}
}



class Customer{
	private static int count = 0;
	
	private final int id = count++;
	
	private Customer(){
		
	}
	
	@Override
	public String toString(){
		return "Customer" + id ;
	}
	
	public static Generator<Customer> generator(){
		return new Generator<Customer>(){
			public Customer next(){
				 return new Customer();
			 }
		};
	}
}


class Teller{
	
	private static int count = 0;
	
	private final int id = count++;
	
	private Teller(){
		
	}
	
	@Override
	public String toString(){
		return "Customer" + id ;
	}
	
	public static Generator<Teller> generator(){
		return new Generator<Teller>(){
			public Teller next(){
				 return new Teller();
			 }
		};
	}
}
