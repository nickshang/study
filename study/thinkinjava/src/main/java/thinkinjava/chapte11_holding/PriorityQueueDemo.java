package thinkinjava.chapte11_holding;


import java.util.*;

/**
 * Created by Think on 2016/5/18.
 */
public class PriorityQueueDemo {

    /**
     *
     */
    private static class Comparatort implements  Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return  o2 -  o1;
        }
    }
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue =
                new PriorityQueue<Integer>();

        // 生产队列
        Random rand = new Random(47);
        for(int i = 0; i < 10; i++)
            priorityQueue.offer(rand.nextInt(i + 10));
        QueueDemo.printQ(priorityQueue);                // 默认优先级 数字越小优先越高

        // 生产队列
        List<Integer> ints = Arrays.asList(25, 22, 20,
                18, 14, 9, 3, 1, 1, 2, 3, 9, 14, 18, 21, 23, 25);
        priorityQueue = new PriorityQueue<Integer>(ints);
        QueueDemo.printQ(priorityQueue);

        // 指定优先级算法
        priorityQueue = new PriorityQueue<Integer>(
                ints.size(), Collections.reverseOrder());           // 指定优先级算法

        priorityQueue = new PriorityQueue<Integer>(
                ints.size(), new Comparatort() );           // 指定优先级算法
        priorityQueue.addAll(ints);
        QueueDemo.printQ(priorityQueue);

        // 生产字符串队列
        String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
        List<String> strings = Arrays.asList(fact.split(""));
        PriorityQueue<String> stringPQ =
                new PriorityQueue<String>(strings);
        QueueDemo.printQ(stringPQ);
        stringPQ = new PriorityQueue<String>(
                strings.size(), Collections.reverseOrder());
        stringPQ.addAll(strings);
        QueueDemo.printQ(stringPQ);

        // 生产字符队列
        Set<Character> charSet = new HashSet<Character>();
        for(char c : fact.toCharArray())
            charSet.add(c); // Autoboxing
        PriorityQueue<Character> characterPQ =
                new PriorityQueue<Character>(charSet);
        QueueDemo.printQ(characterPQ);
    }
}