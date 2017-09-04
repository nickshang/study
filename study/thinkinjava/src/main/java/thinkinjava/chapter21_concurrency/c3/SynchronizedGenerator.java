package thinkinjava.chapter21_concurrency.c3;

public class SynchronizedGenerator  extends IntGenerator{

	 private   int  intcurrentEvenValue = 0 ;
	
	@Override
	public   int next() {
		synchronized (this) {
			++intcurrentEvenValue;
			++intcurrentEvenValue;
		}
		return intcurrentEvenValue;
	}
	
	public static void main(String[] args) {
		EvenChecker.test(new  SynchronizedGenerator() );
	}
	
}
