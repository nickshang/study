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

//        通过实用RTTI，可以发现a是被当B实现的。通过其转型到B，可以调用不在的A中的方法。
//        原本以为interface关键字正保护B,不能访问不是接口实现的方法，但是实际情况不是。

        if (a instanceof B) {
            B b = (B) a;
            b.g();
        }
    }
} /* Output:
B
*///:~
