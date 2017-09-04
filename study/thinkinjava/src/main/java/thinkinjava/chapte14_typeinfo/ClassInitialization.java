﻿package thinkinjava.chapte14_typeinfo;

/**
 * Created by Think on 2016/5/23.
 */

import java.util.Random;

class Initable {
    static final int staticFinal = 47;
    static  int staticFinal2 =
            ClassInitialization.rand.nextInt(1000);

    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2 {
    static int staticNonFinal = 147;

    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {
    static int staticNonFinal = 74;

    static {
        System.out.println("Initializing Initable3");
    }
}

public class ClassInitialization {
    public static Random rand = new Random(47);

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws Exception {
        Class initable = Initable.class;    // 使用类字面常量获取Initable类的引用 —> 不会初始化(（父类和本身）会初执行静态初始化器和静态初始化块)
        System.out.println("After creating Initable ref");

        // Does not trigger initialization:
        System.out.println(Initable.staticFinal);   // 获取编译器常量 —> 不需要初始化
        // Does trigger initialization:
        System.out.println(Initable.staticFinal2);   // 获取编译器常量 —> 不需要初始化

        // Does trigger initialization:
        System.out.println(Initable2.staticNonFinal);   // 获取非编译器常量 —> 需要初始化
        Class initable3 = Class.forName("chapte14_typeinfo.Initable3");   // 使用Class.forName()获取Initable3类的引用 -> 会初始化执行静态初始化器和静态初始化块)

        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);  //
    }


}