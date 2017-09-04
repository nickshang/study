package thinkinjava.typeinfo;
//: typeinfo/SimpleDynamicProxy.java

import java.lang.reflect.*;

/**
 * 代理实例的调用处理程序实现的接口。
 */
class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    public Object
    invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() +
                ", method: " + method + ", args: " + args);
        if (args != null)
            for (Object arg : args)
                System.out.println("  " + arg);
        return method.invoke(proxied, args);
    }
}

/**
 * 测试
 */
class SimpleDynamicProxy {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        // 真实对象
        RealObject real = new RealObject();

        // 调用对象
        consumer(real);

        // 产生代理对象
        // Insert a proxy and call again:
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[]{Interface.class},
                new DynamicProxyHandler(real));

        // 调用对象
        consumer(proxy);
    }
} /* Output: (95% match)	
doSomething
somethingElse bonobo
**** proxy: class $Proxy0, method: public abstract void Interface.doSomething(), args: null
doSomething
**** proxy: class $Proxy0, method: public abstract void Interface.somethingElse(java.lang.String), args: [Ljava.lang.Object;@42e816
  bonobo
somethingElse bonobo
*///:~
