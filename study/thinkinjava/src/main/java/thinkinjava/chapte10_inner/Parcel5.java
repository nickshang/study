package thinkinjava.chapte10_inner;

/**
 * 
 * @��������������ʹ�ã�һ�������ڷ����е��ࣻ
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
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
