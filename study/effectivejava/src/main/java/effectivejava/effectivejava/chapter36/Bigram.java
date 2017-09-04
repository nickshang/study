package effectivejava.effectivejava.chapter36;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 测试不覆盖override注解的错误应用
 * @author NICK
 *
 */
public class Bigram {
	
	private final char first;

	private final char sencond;
	
	public Bigram(char first, char sencond){
		this.first = first;
		this.sencond = sencond;
	}

	//使用注解，有利于检查方法
	@Override
	public boolean equals(Object b){
		if ( !(b instanceof  Bigram) )
			return false;
		Bigram z = (Bigram)b;
		return this.first == z.first && this.sencond == z.sencond;
	}
	
//  没有使用注解导致的问题,导致与父类的方法参数不一样导致，程序逻辑错误，引发在集合类中使用该类出现异常
//	public boolean equals(Bigram b){
//		return this.first == b.first && this.sencond == b.sencond;
//	}
	
	@Override
	public int hashCode(){
		return 31*this.first+ this.sencond;
	}
	
	public static void main (String args[]) {
		
		Set<Bigram> set = new HashSet<Bigram>();
		for (int i=0; i<10; i++){
			for ( char z='a'; z<='z'; z++) {
				Bigram b = new Bigram(z,z);
				set.add(b);
			}
		}
		System.out.println("Set size:" + set.size());
	}
	
}
