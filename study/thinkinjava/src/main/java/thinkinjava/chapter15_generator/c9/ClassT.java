package thinkinjava.chapter15_generator.c9;

public class ClassT  implements thinkinjava.chapter15_generator.c9.Interface1,Interface2 {
	
	public String show(){
		return "show";
	}

	public <T extends thinkinjava.chapter15_generator.c9.Interface1> void M1(T t){
		System.out.println(t.show());
	}
	
	
	public <T extends thinkinjava.chapter15_generator.c9.Interface2> void M2(T t){
		System.out.println(t.show());
	}
	
	
	public static void main(String[] args) {
		thinkinjava.chapter15_generator.c9.Interface1 i1 = new thinkinjava.chapter15_generator.c9.Interface1(){
			public String show(){
				return "Interface1";
			}
		};

		thinkinjava.chapter15_generator.c9.Interface2 i2 = new Interface2(){
			public String show(){
				return "Interface2";
			}
		};
		
		
		ClassT t = new ClassT();
		t.M1(i1);;
		t.M2(i2);;
	}
}
