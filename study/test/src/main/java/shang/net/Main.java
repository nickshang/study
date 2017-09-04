package shang.net;

public class Main {  
	  
    public static void main(String[] args) {  
        InnerTest inner = new InnerTest();  
        Test t = inner.get(3);  
        System.out.println(t.getI());  
    }  
}

class Test {  
  
    private int i;  
  
    public Test(int i) {  
        this.i = i;  
    }  
  
    public int getI() {  
        return i;  
    }  
}

class InnerTest {  
  
    public Test get(int x) {  
        return new Test(x) {  
  
            @Override  
            public int getI() {  
                return super.getI() * 10;  
            }  
        };  
    }  
}  