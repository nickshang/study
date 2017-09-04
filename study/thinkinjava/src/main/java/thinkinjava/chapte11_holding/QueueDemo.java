package thinkinjava.chapte11_holding;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by Think on 2016/5/18.
 */
public class QueueDemo {
    public static void printQ(Queue queue){
        while(queue.peek() != null){
            System.out.print(queue.remove() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Random rand  = new Random(47);
        int t ;
        for(int i = 0; i < 10; i++){
            t = rand.nextInt(i + 10);
            queue.offer(t);         // 将f添加到队列中，如果队列已满，将返回false
            System.out.print(t + " ");
        }
        System.out.println();
        printQ(queue);
    }
}
