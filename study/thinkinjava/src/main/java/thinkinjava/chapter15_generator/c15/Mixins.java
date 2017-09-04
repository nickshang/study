package thinkinjava.chapter15_generator.c15;

import java.util.Date;

/**
 * 
 * @类描述：与接口混合
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class Mixins {
	
	public static void main(String[] args) {
		Mixin mixin1 = new Mixin(), mixin2 = new Mixin();
		mixin1.set(" Mixin 1 ");
		mixin2.set(" Mixin 2 ");
		
		System.out.println( mixin1.get() + " " + mixin1.getSerialNumber() + " " + mixin1.getStamp() );
		System.out.println( mixin2.get() + " " + mixin2.getSerialNumber() + " " + mixin2.getStamp() );
		
	}
}

interface TimeStamped { long getStamp(); }

class TimeStampedImp   implements TimeStamped{
	
	private final long timeStamp ;
	
	public TimeStampedImp(){
		this.timeStamp = new Date().getTime();
	}

	@Override
	public long getStamp() {
		return this.timeStamp;
	}
}

interface SerinalNumbered { long getSerialNumber(); }


class SerinalNumberedImpl implements SerinalNumbered{
	
	private static long  counter = 1;

	private   final long  counterNumber = counter++;
	
	@Override
	public long getSerialNumber() {
		return counterNumber;
	}
}

interface Basic {
	public void set(String val);
	public String get();
}

class BasicImp implements Basic{
	
	private  String value = null ;

	@Override
	public void set(String val) {
		this.value = val;
	}

	@Override
	public String get() {
		return 	this.value ;
	}
}

class Mixin extends BasicImp implements TimeStamped,SerinalNumbered
{
	
	SerinalNumberedImpl  serinal =  new SerinalNumberedImpl();
	TimeStampedImp  time =  new TimeStampedImp();

	@Override
	public long getSerialNumber() {
		return serinal.getSerialNumber();
	}

	@Override
	public long getStamp() {
		return time.getStamp();
	}
	
}














