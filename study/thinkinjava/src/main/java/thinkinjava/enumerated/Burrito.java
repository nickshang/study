//: enumerated/Burrito.java
package thinkinjava.enumerated;

import static thinkinjava.enumerated.Spiciness.*;

/**
 * 静态导入枚举实例
 * 使用static import能够将enum实例的标识符代带入前前的命名空间，无需在用enum类型来修饰enum实例。
 */
public class Burrito {
    thinkinjava.enumerated.Spiciness degree;

    public Burrito(thinkinjava.enumerated.Spiciness degree) {
        this.degree = degree;
    }

    public String toString() {
        return "Burrito is " + degree;
    }

    public static void main(String[] args) {
        System.out.println(new Burrito(NOT));
        System.out.println(new Burrito(MEDIUM));
        System.out.println(new Burrito(HOT));
    }
} /* Output:
Burrito is NOT
Burrito is MEDIUM
Burrito is HOT
*///:~
