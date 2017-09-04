package thinkinjava.chapter15_generator.c8;

public class ClassFactoryTest {
	public static  void main(String[] a) {
		ClassFactory<Employee> c = new ClassFactory<>(Employee.class);
		System.out.println(c.get());
	}
	
}

class Employee{
	
	public String toString(){
		return "Employee";
	}
}
