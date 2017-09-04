package thinkinjava.chapter5_init;

public class Base
{
    public String baseName = "base";
    
    {
    	System.out.println("base");
    }
    
    public Base()
    {
        callName();
    }

    public void callName()
    {
        System. out. println(baseName);
    }

    static class Sub extends Base
    {
    	public String baseName = "sub";
    	
    	{            		
    		System.out.println("Sub");
    	}
    	
        public void callName()
        {
            System. out. println (baseName) ;
        }
        
    }
    public static void main(String[] args)
    {
        Base b = new Sub();
    }
}
