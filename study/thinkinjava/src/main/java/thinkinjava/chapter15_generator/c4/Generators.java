package thinkinjava.chapter15_generator.c4;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @类描述： 用于批量生成
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月27日 下午10:30:49
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class Generators {
	public final static <T> Collection<T> fill( Collection<T> coll,
			Collection<T> gen, int n){
		Iterator<T> it = gen.iterator();
		for(int i = 0; i < n; i++ ){
			coll.add(it.next());
		}
		return coll;
	}
	
	
	public final static  <T> List<T> fill( List<T> coll,
			List<T> gen, int n){
		Iterator<T> it = gen.iterator();
		for(int i = 0; i < n; i++ ){
			coll.add(it.next());
		}
		return coll;
	}
	
	
	public final static  <T> Queue<T> fill( Queue<T> coll,
			Queue<T> gen, int n){
		Iterator<T> it = gen.iterator();
		for(int i = 0; i < n; i++ ){
			coll.add(it.next());
		}
		return coll;
	}
	
	
	public final static  <T> Set<T> fill( Set<T> coll,
			Set<T> gen, int n){
		Iterator<T> it = gen.iterator();
		for(int i = 0; i < n; i++ ){
			coll.add(it.next());
		}
		return coll;
	}
	
	
	
	public final static  <T> LinkedList<T> fill( LinkedList<T> coll,
			LinkedList<T> gen, int n){
		Iterator<T> it = gen.iterator();
		for(int i = 0; i < n; i++ ){
			coll.add(it.next());
		}
		return coll;
	}
}
