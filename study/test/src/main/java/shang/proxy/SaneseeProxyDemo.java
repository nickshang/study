package shang.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 利用代理可以在运行时创建一个实现了一组给定接口的新类，这种功能只有在编译时无法确定需要实现哪个接口时才有必要使用。
 * 我们需要提供一个实现InvocationHandler接口的类来处理调用过程。
 * 
 * 来源：http://www.sanesee.com/article/15-forgettable-java-questions?utm_source=tuicool&utm_medium=referral
 * @类描述：
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月4日 上午9:21:23
 * @修改人：NICK
 * @修改时间：2016年4月4日 上午9:21:23
 * @修改备注：
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class SaneseeProxyDemo{
	  public static void main(String[] args) {
	      //配置第一个代理aProxyInstance，用于代理Integer类型的a。
	      Integer a = 1;
	      InvocationHandler aHandler = new SaneseeHandler (a);
    	    // 其中第一个参数null表示使用默认的类加载器，	 //第二个参数表明需要代理类实现的接口，第三个参数为调用处理器类
	      Object aProxyInstance = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, aHandler); 
	      //配置第一个代理bProxyInstance，用于代理Integer类型的b。
	      Integer b = 2;
	      InvocationHandler bHandler = new SaneseeHandler (b);
	      Object bProxyInstance = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, bHandler);
	      //输出两个代理类的比较结果
	      Comparable aComparable = (Comparable)aProxyInstance;
	      Comparable bComparable = (Comparable)bProxyInstance;
	      System.out.println(aComparable.compareTo(b));
	    }
	  }

class SaneseeHandler implements InvocationHandler
{
  //需要代理的对象
  private Object target;
  public SaneseeHandler (Object t)
  {
    target = t;
  }
  public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
  {
    //打印被代理的对象
    System.out.print(target);
    // 打印方法名
    System.out.print("." + m.getName() + "(");
    // 打印参数
    if (args != null)
    {
      for (int i = 0; i < args.length; i++)
      {
        System.out.print(args[i]);
        if (i < args.length - 1) System.out.print(", ");
      }
    }
    System.out.println(")");
    // 调用方法
    return m.invoke(target, args);
  }
}