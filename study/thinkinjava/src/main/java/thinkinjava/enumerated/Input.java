//: enumerated/Input.java
package thinkinjava.enumerated;

import java.util.*;

/**
 * 投入币种
 * 使用enum实现状态机
 * 除两个特殊的Input实例外，其他input都有相应的价格，因此在接口中定义了amount()方法。
 * 对这两个amount()方法并不合适，所以如果调用amout()方法就回异常抛出。
 *
 */
public enum Input {
    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
    ABORT_TRANSACTION {
        public int amount() { // Disallow
            throw new RuntimeException("ABORT.amount()");
        }
    },
    STOP { // This must be the last instance.

        public int amount() { // Disallow
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };
    int value; // In cents

    Input(int value) {
        this.value = value;
    }

    Input() {
    }

    int amount() {
        return value;
    }

    ; // In cents
    static Random rand = new Random(47);

    public static Input randomSelection() {
        // Don't include STOP:
        return values()[rand.nextInt(values().length - 1)];
    }
} ///:~
