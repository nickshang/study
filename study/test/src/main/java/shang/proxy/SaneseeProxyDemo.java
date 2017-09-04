package shang.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ���ô������������ʱ����һ��ʵ����һ������ӿڵ����࣬���ֹ���ֻ���ڱ���ʱ�޷�ȷ����Ҫʵ���ĸ��ӿ�ʱ���б�Ҫʹ�á�
 * ������Ҫ�ṩһ��ʵ��InvocationHandler�ӿڵ�����������ù��̡�
 * 
 * ��Դ��http://www.sanesee.com/article/15-forgettable-java-questions?utm_source=tuicool&utm_medium=referral
 * @��������
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��4�� ����9:21:23
 * @�޸��ˣ�NICK
 * @�޸�ʱ�䣺2016��4��4�� ����9:21:23
 * @�޸ı�ע��
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public class SaneseeProxyDemo{
	  public static void main(String[] args) {
	      //���õ�һ������aProxyInstance�����ڴ���Integer���͵�a��
	      Integer a = 1;
	      InvocationHandler aHandler = new SaneseeHandler (a);
    	    // ���е�һ������null��ʾʹ��Ĭ�ϵ����������	 //�ڶ�������������Ҫ������ʵ�ֵĽӿڣ�����������Ϊ���ô�������
	      Object aProxyInstance = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, aHandler); 
	      //���õ�һ������bProxyInstance�����ڴ���Integer���͵�b��
	      Integer b = 2;
	      InvocationHandler bHandler = new SaneseeHandler (b);
	      Object bProxyInstance = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, bHandler);
	      //�������������ıȽϽ��
	      Comparable aComparable = (Comparable)aProxyInstance;
	      Comparable bComparable = (Comparable)bProxyInstance;
	      System.out.println(aComparable.compareTo(b));
	    }
	  }

class SaneseeHandler implements InvocationHandler
{
  //��Ҫ����Ķ���
  private Object target;
  public SaneseeHandler (Object t)
  {
    target = t;
  }
  public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
  {
    //��ӡ������Ķ���
    System.out.print(target);
    // ��ӡ������
    System.out.print("." + m.getName() + "(");
    // ��ӡ����
    if (args != null)
    {
      for (int i = 0; i < args.length; i++)
      {
        System.out.print(args[i]);
        if (i < args.length - 1) System.out.print(", ");
      }
    }
    System.out.println(")");
    // ���÷���
    return m.invoke(target, args);
  }
}