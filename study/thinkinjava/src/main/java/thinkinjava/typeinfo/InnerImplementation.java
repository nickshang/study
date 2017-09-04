package thinkinjava.typeinfo;
//: typeinfo/InnerImplementation.java
// Private inner classes can't hide from reflection.

import thinkinjava.typeinfo.interfacea.A;

import static thinkinjava.net.mindview.util.Print.print;

class InnerA {
    // 将接口定义为内部类，通过反射仍然调用类的方法
    private static class C implements A {
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

    public static A makeA() {
        return new C();
    }
}

public class InnerImplementation {
    public static void main(String[] args) throws Exception {
        A a = InnerA.makeA();
        a.f();
        System.out.println(a.getClass().getName());
        // Reflection still gets into the private class:
//       typeinfo.HiddenImplementation.callHiddenMethod(a, "g");
//        typeinfo.HiddenImplementation.callHiddenMethod(a, "u");
//        thinkinjava.typeinfo.HiddenImplementation.callHiddenMethod(a, "v");
//        thinkinjava.typeinfo.HiddenImplementation.callHiddenMethod(a, "w");
    }
} /* Output:
public C.f()
InnerA$C
public C.g()
package C.u()
protected C.v()
private C.w()
*///:~
