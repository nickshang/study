package thinkinjava.enumerated;//: enumerated/EnumClass.java
// Capabilities of the Enum class

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

enum Shrubbery {GROUND, CRAWLING, HANGING}

/**
 * Enum类方法实例
 */
public class EnumClass {
    public static void main(String[] args) {
        for (Shrubbery s : Shrubbery.values()) {
            print(s + " ordinal: " + s.ordinal());              // 返回enum中的声明的顺序
            printnb(s.compareTo(Shrubbery.CRAWLING) + " ");     // Enum实现了Comparable接口，所以具有compareTo()方法
            printnb(s.equals(Shrubbery.CRAWLING) + " ");        // 编译器会自动为你提供equals()和hashCode()方法
            print(s == Shrubbery.CRAWLING);                     // 编译器会自动为你提供equals()和hashCode()方法
            print(s.getDeclaringClass());                       // 获取enum定义类
            print(s.name());                                    // 获取实例名
            print("----------------------");
        }
        // Produce an enum value from a string name:
        for (String s : "HANGING CRAWLING GROUND".split(" ")) {
            Shrubbery shrub = Enum.valueOf(Shrubbery.class, s); // 返回带指定名称的指定枚举类型的枚举常量。
            print(shrub);
        }
    }
} /* Output:
GROUND ordinal: 0
-1 false false
class Shrubbery
GROUND
----------------------
CRAWLING ordinal: 1
0 true true
class Shrubbery
CRAWLING
----------------------
HANGING ordinal: 2
1 false false
class Shrubbery
HANGING
----------------------
HANGING
CRAWLING
GROUND
*///:~
