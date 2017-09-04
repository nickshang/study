//: typeinfo/packageaccess/HiddenC.java
package thinkinjava.typeinfo.packageaccess;


import thinkinjava.typeinfo.interfacea.A;

import static thinkinjava.net.mindview.util.Print.print;

// C定义为缺省的访问权限，只能在本类和包内访问
class C implements A {
    /**
     * NAME
     */
    private String a ;


    public void f() {
        print("public C.f()");
    }

    public void g() {
        print("public C.g()");
    }

    void u() {
        print("package C.u()");
    }

    protected void v() {
        print("protected C.v()");
    }

    private void w() {
        print("private C.w()");
    }
}

public class HiddenC {
    public static A makeA() {
        return new C();
    }
} ///:~
