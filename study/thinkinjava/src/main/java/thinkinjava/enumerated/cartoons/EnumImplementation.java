//: enumerated/cartoons/EnumImplementation.java
// An enum can implement an interface
package thinkinjava.enumerated.cartoons;

import thinkinjava.net.mindview.util.Generator;

import java.util.Random;

enum CartoonCharacter
        implements Generator<CartoonCharacter> {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
    private Random rand = new Random(47);

    public CartoonCharacter next() {
        return values()[rand.nextInt(values().length)];
    }
}

/**
 * enum都继承了自java.lang.Enum类。
 * 所以要创建一个Enum类是可以实现一个或者多个接口
 */
public class EnumImplementation {
    public static <T> void printNext(Generator<T> rg) {
        System.out.print(rg.next() + ", ");
    }

    public static void main(String[] args) {
        // Choose any instance:
        CartoonCharacter cc = CartoonCharacter.BOB;
        for (int i = 0; i < 10; i++)
            printNext(cc);
    }
} /* Output:
BOB, PUNCHY, BOB, SPANKY, NUTTY, PUNCHY, SLAPPY, NUTTY, NUTTY, SLAPPY,
*///:~
