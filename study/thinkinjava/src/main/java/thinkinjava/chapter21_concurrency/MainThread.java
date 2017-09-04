package thinkinjava.chapter21_concurrency;

public class MainThread {

	public static void main(String[] args) {
		LiftOff liftOff = new LiftOff();
		liftOff.run();
		System.out.println("-");
		liftOff.run();
	}

	
}
