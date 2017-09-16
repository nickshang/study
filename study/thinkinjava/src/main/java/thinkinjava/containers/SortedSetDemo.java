package thinkinjava.containers;//: containers/SortedSetDemo.java
// What you can do with a TreeSet.

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import static net.mindview.util.Print.print;

public class SortedSetDemo {
    public static void main(String[] args) {
        SortedSet<String> sortedSet = new TreeSet<String>();
        Collections.addAll(sortedSet,
                "one two three four five six seven eight"
                        .split(" "));
//        print(sortedSet);
        String low = sortedSet.first();                 // 返回容器中第一元素
        String high = sortedSet.last();                 // 返回容器中的最末一个元素
        print(low);
        print(high);
        Iterator<String> it = sortedSet.iterator();
        for (int i = 0; i <= 6; i++) {
            if (i == 3) low = it.next();
            if (i == 6) high = it.next();
            else it.next();
        }
        print(low);
        print(high);
        print(sortedSet.subSet(low, high));             // 生成Set的子集，范围从fromElement(包含)到toElement(不包含)
        print(sortedSet.headSet(high));                 // 生成此Set的子集，有小于toElement的元素组
        print(sortedSet.tailSet(low));                  // 生成此Set的子集，有大于或等于fromElement的元素组成。
    }
} /* Output:
[eight, five, four, one, seven, six, three, two]
eight
two
one
two
[one, seven, six, three]
[eight, five, four, one, seven, six, three]
[one, seven, six, three, two]
*///:~
