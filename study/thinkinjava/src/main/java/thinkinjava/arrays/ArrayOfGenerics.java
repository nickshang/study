package thinkinjava.arrays;//: arrays/ArrayOfGenerics.java
// It is possible to create arrays of generics.

import java.util.*;

public class ArrayOfGenerics {

    // 可以定义持有泛型的的数组对象，不能创建实际的持有泛型的数组对象，但是可以创建非泛型的数组，然后将其转型。
    public static void main(String[] args) {

        // 数组是个协变类型，因此List<String>[]也是Object[],
        // 可以利用这一点，将一个ArrayList<Integer>赋值到你的数组中，而不会有任何编译期或者运行时错误。
        List<String>[] ls;
        List[] la = new List[10];
        ls = (List<String>[]) la; // "Unchecked" warning
        ls[0] = new ArrayList<String>();
        // Compile-time checking produces an error:
        //! ls[1] = new ArrayList<Integer>();

        //
        // The problem: List<String> is a subtype of Object
        Object[] objects = ls; // So assignment is OK
        // Compiles and runs without complaint:
        objects[1] = new ArrayList<Integer>();

        // However, if your needs are straightforward it is
        // possible to create an array of generics, albeit
        // with an "unchecked" warning:
        List<BerylliumSphere>[] spheres =
                (List<BerylliumSphere>[]) new List[10];
        for (int i = 0; i < spheres.length; i++)
            spheres[i] = new ArrayList<BerylliumSphere>();
    }
} ///:~
