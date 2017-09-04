package effectivejava.effectivejava.chapter69;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 使用并发集合模拟实现String.intern方法：使用内部方法从数据池中进获取数据
 * @author Think
 *
 */
public class StringIntern {
	private final ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
	
	public String intern(String s){
//		//第一种解决方案
//		String  p =  map.putIfAbsent(s, s);
//		return p == null ? s : p;
		
		String  p = map.get(s);
		if ( p == null ) {
			p = map.putIfAbsent(s, s);
			if ( p == null )
				return s;
		} 
		return p;
	}
	
	public static void main(String[] args) {
		StringIntern si = new StringIntern();
		System.out.println( si.intern("111") ) ;
		System.out.println( si.intern("111") ) ;
	}
}
