package thinkinjava.chapter15_generator.c2;

import thinkinjava.net.mindview.util.ThreeTuple;
import thinkinjava.net.mindview.util.TwoTuple;
import thinkinjava.net.mindview.util.FourTuple;
import thinkinjava.net.mindview.util.FiveTuple;

/**
 * 
 * @类描述： 元组类库测试
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月24日 上午10:47:49
 * @修改人：NICK
 * @修改时间：2016年4月24日 上午10:47:49
 * @修改备注：
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class TupleTest {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static TwoTuple<String,Integer> f(){
		return new TwoTuple("h1",47);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static ThreeTuple<Amphibian,String,Integer> g(){
		return new ThreeTuple(new Amphibian(),"h1",47);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static FourTuple<Vehicel,Amphibian,String,Integer> h(){
		return new FourTuple(new Vehicel(),new Amphibian(),"h1",47);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static FiveTuple<Vehicel,Amphibian,String,Integer,Double> k(){
		return new FiveTuple(new Vehicel(),new Amphibian(),"h1",47,11.1d);
	}
	
	public static void main(String[] args) {
		TwoTuple<String,Integer> ttsi = f();
		System.out.println(ttsi);
		System.out.println(g());
		System.out.println(h());
		System.out.println(k());
		
	}
}

class Amphibian{}

class Vehicel{}
