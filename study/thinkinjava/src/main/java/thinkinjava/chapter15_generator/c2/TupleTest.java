package thinkinjava.chapter15_generator.c2;

import net.mindview.util.ThreeTuple;
import net.mindview.util.TwoTuple;
import net.mindview.util.FourTuple;
import net.mindview.util.FiveTuple;

/**
 * 
 * @�������� Ԫ��������
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��24�� ����10:47:49
 * @�޸��ˣ�NICK
 * @�޸�ʱ�䣺2016��4��24�� ����10:47:49
 * @�޸ı�ע��
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
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
