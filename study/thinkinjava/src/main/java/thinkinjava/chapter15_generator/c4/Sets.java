package thinkinjava.chapter15_generator.c4;

import java.util.Set;
import java.util.HashSet;

public class Sets {
	
	/**
	 * 
	 * @描述: 实现并集
	 * @param a 集合
	 * @param b 集合 
	 * @return  并集集合
	 * @返回类型 Set<T>
	 * @创建人 NICK
	 * @创建时间 2016年4月26日 下午10:20:33
	 * @since
	 * @throws
	 */
	public static <T> Set<T> union(Set<T> a, Set<T> b){
		 Set<T> result = new HashSet<T>(a);
		 result.addAll(b);
		 return result ;
	}
	
	/**
	 * 
	 * @描述: 实现交集
	 * @param a 集合
	 * @param b 集合 
	 * @return  交集集合
	 * @返回类型 Set<T>
	 * @创建人 NICK
	 * @创建时间 2016年4月26日 下午10:20:33
	 * @since
	 * @throws
	 */
	public static <T> Set<T> intersection(Set<T> a, Set<T> b){
		Set<T> result = new HashSet<T>(a);
		result.retainAll(b);
		 return result ;
	}
	
	/**
	 * 
	 * @描述:实现差集
	 * @param a 集合
	 * @param b 集合
	 * @return
	 * @返回类型 Set<T>
	 * @创建人 NICK
	 * @创建时间 2016年4月26日 下午10:25:55
	 * @since
	 * @throws
	 */
	public static <T> Set<T> difference(Set<T> a, Set<T> b){
		Set<T> result = new HashSet<T>(a);  				// 重复复制一份不改变原来的
		result.removeAll(b);
		 return result ;
	}
	
	

}
