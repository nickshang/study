package thinkinjava.typeinfo;
//: typeinfo/SimpleProxyDemo.java

import static  thinkinjava.net.mindview.util.Print.*;

/**
 * 接口
 */
interface Interface {
    void doSomething();

    void somethingElse(String arg);
}

/**
 * 真实对象
 */
class RealObject implements Interface {
    public void doSomething() {
        print("doSomething");
    }

    public void somethingElse(String arg) {
        print("somethingElse " + arg);
    }
}

/**
 * 代理对象->代理真实对象
 */
class SimpleProxy implements Interface {
    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    public void doSomething() {
        print("SimpleProxy doSomething");
        proxied.doSomething();
    }

    public void somethingElse(String arg) {
        print("SimpleProxy somethingElse " + arg);
        proxied.somethingElse(arg);
    }
}

class SimpleProxyDemo {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }


    /**
     * 测试
     */
    public static void main(String[] args) {
        consumer(new RealObject());                             //  因为consumer方法接收的是Interface接口，传入是new RealObject()、new SimpleProxy(new RealObject())
        consumer(new SimpleProxy(new RealObject()));            //  方法内部不进行区分
    }
} /* Output:
doSomething
somethingElse bonobo
SimpleProxy doSomething
doSomething
SimpleProxy somethingElse bonobo
somethingElse bonobo
*///:~
