package thinkinjava.typeinfo;
//: typeinfo/InterfaceViolation.java
// Sneaking around an interface.

import  thinkinjava.typeinfo.interfacea.*;

class B implements A {
    public void f() {
    }

    public void g() {
    }
}

public class InterfaceViolation {
    public static void main(String[] args) {
        A a = new B();
        a.f();
        // a.g(); // Compile error
        System.out.println(a.getClass().getName());

//        ͨ��ʵ��RTTI�����Է���a�Ǳ���Bʵ�ֵġ�ͨ����ת�͵�B�����Ե��ò��ڵ�A�еķ�����
//        ԭ����Ϊinterface�ؼ���������B,���ܷ��ʲ��ǽӿ�ʵ�ֵķ���������ʵ��������ǡ�

        if (a instanceof B) {
            B b = (B) a;
            b.g();
        }
    }
} /* Output:
B
*///:~
