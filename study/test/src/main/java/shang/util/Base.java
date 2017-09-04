package shang.util;
 


public class Base
{
    private String baseName = "base";
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
        public final static String baseName = "sub";
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