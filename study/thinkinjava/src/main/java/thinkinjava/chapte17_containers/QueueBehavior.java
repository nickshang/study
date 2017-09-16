package thinkinjava.chapte17_containers;//: containers/QueueBehavior.java
// Compares the behavior of some of the queues

import java.util.concurrent.*;
import java.util.*;

import net.mindview.util.*;

public class QueueBehavior {

    private static int count = 10;

    static <T> void test(Queue<T> queue, Generator<T> gen) {

        /**
         *
         * offer
         * 将指定的元素插入此队列（如果立即可行且不会违反容量限制），当使用有容量限制的队列时，
         * 此方法通常要优于 add(E)，后者可能无法插入元素，而只是抛出一个异常。
         * 参数：
         * e - 要添加的元素
         * 返回：
         * 如果该元素已添加到此队列，则返回 true；否则返回 false
         * 抛出：
         * ClassCastException - 如果指定元素的类不允许将其添加到此队列
         * NullPointerException - 如果指定元素为 null 并且此队列不允许 null 元素
         * IllegalArgumentException - 如果此元素的某些属性不允许将其添加到此队列
         */
        for (int i = 0; i < count; i++)
            queue.offer(gen.next());

        /**
         * peek 方法
         * 获取但不移除此队列的头；如果此队列为空，则返回 null。
         * 返回：
         * 此队列的头；如果此队列为空，则返回 null
         */
        while (queue.peek() != null)
            System.out.print(queue.remove() + " ");
        System.out.println();

    }

    static class Gen implements Generator<String> {
        String[] s = ("one two three four five six seven " +
                "eight nine ten").split(" ");
        int i;

        public String next() {
            return s[i++];
        }
    }

    public static void main(String[] args) {
        test(new LinkedList<String>(), new Gen());
        test(new PriorityQueue<String>(), new Gen());
        test(new ArrayBlockingQueue<String>(count), new Gen());
        test(new ConcurrentLinkedQueue<String>(), new Gen());
        test(new LinkedBlockingQueue<String>(), new Gen());
        test(new PriorityBlockingQueue<String>(), new Gen());
    }
}

/* Output:
one two three four five six seven eight nine ten
eight five four nine one seven six ten three two
one two three four five six seven eight nine ten
one two three four five six seven eight nine ten
one two three four five six seven eight nine ten
eight five four nine one seven six ten three two
*///:~
