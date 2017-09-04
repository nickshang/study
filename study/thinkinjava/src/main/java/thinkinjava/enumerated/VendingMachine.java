//: enumerated/VendingMachine.java
// {Args: VendingMachineInput.txt}
package thinkinjava.enumerated;

import  thinkinjava.net.mindview.util.Generator;
import  thinkinjava.net.mindview.util.TextFile;

import java.util.EnumMap;
import java.util.Iterator;

import static thinkinjava.enumerated.Input.*;
import static thinkinjava.net.mindview.util.Print.print;

/**
 *  种类
 */
enum Category {
    MONEY(NICKEL, DIME, QUARTER, DOLLAR),
    ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP),
    QUIT_TRANSACTION(ABORT_TRANSACTION),
    SHUT_DOWN(STOP);
    private Input[] values;

    Category(Input... types) {
        values = types;
    }

    private static EnumMap<Input, Category> categories =
            new EnumMap<Input, Category>(Input.class);

    static {
        for (Category c : Category.class.getEnumConstants())
            for (Input type : c.values)
                categories.put(type, c);
    }

    public static Category categorize(Input input) {
        return categories.get(input);
    }
}

/**
 * 售货机
 */
public class VendingMachine {
    private static State state = State.RESTING;
    private static int amount = 0;
    private static Input selection = null;

    enum StateDuration {TRANSIENT} // Tagging enum

    /**
     * 使用enum对售货机状态进行封装，并实现利用抽象方法实现策略模式
     */
    enum State {
        // 进行分类查找 -> 用户塞入钞票 -> 选择货物 —> 操作被取消 -> 机器停止
        // 分类查找 -> 选择不同的钞票，可以选择不同的货物
        // Category enum 将不同类型的input进行分组，因此，可以使用categorize()方法为switch语句生成恰当的cateroy实例
        // 确保EnumMap确保了在其中进行查询时的效率和安全。
        RESTING {
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        ADDING_MONEY {                                      //
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if (amount < selection.amount())
                            print("Insufficient money for " + selection);
                        else state = DISPENSING;
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        DISPENSING(StateDuration.TRANSIENT) {           // 分发
            void next() {
                print("here is your " + selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },
        GIVING_CHANGE(StateDuration.TRANSIENT) {        // 瞬时 -> 改变合计 ->  终止服务
            void next() {
                if (amount > 0) {
                    print("Your change: " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        },
        TERMINAL {                                      // 终止服务
            void output() {
                print("Halted");
            }
        };
        private boolean isTransient = false;

        State() {
        }

        State(StateDuration trans) {
            isTransient = true;
        }

        void next(Input input) {
            throw new RuntimeException("Only call " +
                    "next(Input input) for non-transient states");
        }

        void next() {
            throw new RuntimeException("Only call next() for " +
                    "StateDuration.TRANSIENT states");
        }

        void output() {
            print(amount);
        }
    }

    static void run(Generator<Input> gen) {
        // 检查售货机状态 -> 不是停机状态
        while (state != State.TERMINAL) {

            // 售货机对投币进行处理
            state.next(gen.next());
            while (state.isTransient)
                state.next();

            // 出售货物
            state.output();

        }
    }

    public static void main(String[] args) {
        // 随机生成一种币
        Generator<Input> gen = new RandomInputGenerator();

        // 判断输入参数 -> 根据输入参数产生币种
        if (args.length == 1)
            gen = new FileInputGenerator(args[0]);

        // 售货机处理
        run(gen);
    }
}

// For a basic sanity check:
class RandomInputGenerator implements Generator<Input> {
    public Input next() {
        return Input.randomSelection();
    }
}

// Create Inputs from a file of ';'-separated strings:
class FileInputGenerator implements Generator<Input> {
    private Iterator<String> input;

    public FileInputGenerator(String fileName) {
        input = new TextFile(fileName, ";").iterator();
    }

    public Input next() {
        if (!input.hasNext())
            return null;
        return Enum.valueOf(Input.class, input.next().trim());
    }
} /* Output:
25
50
75
here is your CHIPS
0
100
200
here is your TOOTHPASTE
0
25
35
Your change: 35
0
25
35
Insufficient money for SODA
35
60
70
75
Insufficient money for SODA
75
Your change: 75
0
Halted
*///:~
