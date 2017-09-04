package thinkinjava.chapte13_string;

/**
 * Created by Think on 2016/5/25.
 */
import java.util.*;

public class InfiniteRecursion {
    public String toString() {
//        return " InfiniteRecursion address: " + super.toString() + "\n";
          return " InfiniteRecursion address: " + this.toString() + "\n";
        // 使用this打印对象信息，会导致递归调用
        // 应该使用Object.toString()方法，此处使用super.toString()方法
    }
    public static void main(String[] args) {
        List<InfiniteRecursion> v =
                new ArrayList<InfiniteRecursion>();
        for(int i = 0; i < 10; i++)
            v.add(new InfiniteRecursion());
        System.out.println(v);
    }
}