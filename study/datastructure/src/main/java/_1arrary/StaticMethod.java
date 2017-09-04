package _1arrary;

public class StaticMethod{  
	  
    public static void main(String[] args) {  
  
        Animal animal = new Cat();  
        animal.output();  
  
        Cat cat = new Cat();  
        cat.output();  
          
    }  
}  
  
class Animal{  
  
    public static void output(){  
        System.out.println("Animal");  
    }  
  
}  
  
  
class Cat extends Animal{  
      
    public static void output(){  
        System.out.println("Cat");  
  
    }  
  
    /* 
        静态方法(Class Methods)只能继承，不能重写。 
        参见:http://docs.oracle.com/javase/tutorial/java/IandI/override.html 
    */  
}  