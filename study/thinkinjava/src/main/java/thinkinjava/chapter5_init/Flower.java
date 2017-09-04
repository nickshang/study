package thinkinjava.chapter5_init;

public class Flower {
	private int i ;
	private String s ;
	
	public Flower(int i){
		this.i = i;
	}

	public Flower(String s){
		this.s = s;
	}
	
	public Flower(int i,String s){
	}
	
	public static void main(String[] args) {
		int i = 1;
		int o = 0;
		while( i < 1_000_000){
			i = i*2;
			System.out.println(o++);
		}
	}

}
