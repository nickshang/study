package effectivejava.effectivejava.chapter25;

import java.util.ArrayList;
import java.util.List;


/**
 * 功能描述：学习泛型相关规范
 * @author NICK
 *
 */
public class Etest {
	
	public <P> void say(P msg){};
	

	public void t1(){
		
		List<?> lst = new ArrayList<P>();
//		lst = new ArrayList<S>();
		
		// 以下这句将导致编译失败
		P p1 = new P();
//		lst.addAll( p1 );
		lst.add( null );
		
	}
	
}
