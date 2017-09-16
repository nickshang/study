package thinkinjava.containers;//: containers/CollectionDataTest.java

import net.mindview.util.CollectionData;
import net.mindview.util.Generator;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 数据类实例生产器
 */
class Government implements Generator<String> {

    String[] foundation = ("strange women lying in ponds " +
            "distributing swords is no basis for a system of " +
            "government").split(" ");
    private int index;

    public String next() {
        return foundation[index++];
    }
}

public class CollectionDataTest {
    public static void main(String[] args) {

        // 所有的Collection子类型都一个接收另一个Collection对象的构造器，
        // 用所接收的Collection对象中的元素来填充新的容器。
        Set<String> set = new LinkedHashSet<String>(
                new CollectionData<String>(new Government(), 15));
        // Using the convenience method:
        set.addAll(CollectionData.list(new Government(), 15));
        System.out.println(set);
    }
} /* Output:
[strange, women, lying, in, ponds, distributing, swords, is, no, basis, for, a, system, of, government]
*///:~
