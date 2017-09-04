package thinkinjava.typeinfo;
//: typeinfo/HiddenImplementation.java
// Sneaking around package access.

import  thinkinjava.typeinfo.interfacea.*;
import  thinkinjava.typeinfo.packageaccess.*;

import java.lang.reflect.*;

public class HiddenImplementation {
    public static void main(String[] args) throws Exception {
        A a = HiddenC.makeA();
        a.f();
        System.out.println(a.getClass().getName());

        // 因为类C为包内的访问权限，所以试图访问类C，出现异常。 成功的起到了隔离的作用

        // Compile error: cannot find symbol 'C':
    /* if(a instanceof C) {
      C c = (C)a;
      c.g();
    } */
        // Oops! Reflection still allows us to call g():
        callHiddenMethod(a, "g");
        // And even methods that are less accessible!
        callHiddenMethod(a, "u");
        callHiddenMethod(a, "v");
        callHiddenMethod(a, "w");
    }

    // 通过反射还是可以访问类C的方法。
    // 需要知道方法名称，并setAccessible(ture)。
    static void callHiddenMethod(Object a, String methodName)
            throws Exception {
        Method g = a.getClass().getDeclaredMethod(methodName);
        g.setAccessible(true);
        g.invoke(a);
    }
} /* Output:
public C.f()
typeinfo.packageaccess.C
public C.g()
package C.u()
protected C.v()
private C.w()
*///:~
