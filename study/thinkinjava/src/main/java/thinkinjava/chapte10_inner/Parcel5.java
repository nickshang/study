package thinkinjava.chapte10_inner;

/**
 * 
 * @类描述：匿名类使用：一个定义在方法中的类；
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class Parcel5 {
	public Destination destination(String s){
		
		class PDestination implements Destination{

			private String label;

			public PDestination(String l){
				this.label = l;
			}

			@Override
			public String readLabel() {
				return this.label;
			}
		}
		
		return new PDestination(s);
	}

	public static void main(String[] args) {
		Parcel5 d = new Parcel5();
		System.out.println( d.destination("1ss").readLabel() );
	}
}
