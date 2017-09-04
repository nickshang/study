//: net/mindview/util/Enums.java
package thinkinjava.net.mindview.util;

import java.util.*;

/**
 * 从enum 实例中随机选择，利用泛型，从而使得这个工作跟一般化，并将加入到外面的工作库中。
 */
public class Enums {
    private static Random rand = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
} ///:~
