package thinkinjava.chapter5_init;

public class Mugs {
	
	Mug m1;
	Mug m2;
	
	{
		m1  = new Mug(1);
		m1  = new Mug(2);
	}
	
	public Mugs(){
		System.out.println("mugs");
	}

	public static void main(String[] args) {
		Mugs m = new Mugs();
	}
}
//Êä³ö£º
//Mug(1)
//Mug(2)
//mugs


class Mug{
	public Mug(int i){
		System.out.println("Mug(" + i +")");
	}
}