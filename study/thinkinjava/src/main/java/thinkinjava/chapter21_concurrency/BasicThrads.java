package thinkinjava.chapter21_concurrency;

public class BasicThrads {
	
	public static void main(String[] args) {
		Thread t = new Thread(new LiftOff());
		t.start();
		System.out.println("Watiting for LiftOff");
	}
}
