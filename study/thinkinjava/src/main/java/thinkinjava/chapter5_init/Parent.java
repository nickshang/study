package thinkinjava.chapter5_init;

public  class Parent{
    int x=10;
    public Parent(){
         add(2);
    }
    void add(int y){
         x+=y;
    }
    
    public static void main(String[] args){
        Parent p=new Child();
        System.out.println(p.x);
        System.out.println( ((Child)p).x);
   } 
}
class Child extends Parent{
    int x=9;
    void add(int y){
    	System.out.println(" child add ");
    	System.out.println(" y: " + y );  // 2
    	System.out.println(" x: " + x);	  // 0
         x+=y;
    }
    
}
