package thinkinjava.typeinfo;
//: typeinfo/SimpleProxyDemo.java

import static net.mindview.util.Print.print;

/**
 * �ӿ�
 */
interface Interface {
    void doSomething();

    void somethingElse(String arg);
}

/**
 * ��ʵ����
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
 * �������->������ʵ����
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
     * ����
     */
    public static void main(String[] args) {
        consumer(new RealObject());                             //  ��Ϊconsumer�������յ���Interface�ӿڣ�������new RealObject()��new SimpleProxy(new RealObject())
        consumer(new SimpleProxy(new RealObject()));            //  �����ڲ�����������
    }
} /* Output:
doSomething
somethingElse bonobo
SimpleProxy doSomething
doSomething
SimpleProxy somethingElse bonobo
somethingElse bonobo
*///:~
