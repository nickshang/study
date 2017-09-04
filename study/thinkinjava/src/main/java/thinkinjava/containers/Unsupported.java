package thinkinjava.containers;//: containers/Unsupported.java
// Unsupported operations in Java containers.

import java.util.*;

public class Unsupported {
    static void test(String msg, List<String> list) {
        System.out.println("--- " + msg + " ---");
        Collection<String> c = list;
        Collection<String> subList = list.subList(1, 8);
        // Copy of the sublist:
        Collection<String> c2 = new ArrayList<String>(subList);
        try {
            c.retainAll(c2);
        } catch (Exception e) {
            System.out.println("retainAll(): " + e);
        }
        try {
            c.removeAll(c2);
        } catch (Exception e) {
            System.out.println("removeAll(): " + e);
        }
        try {
            c.clear();
        } catch (Exception e) {
            System.out.println("clear(): " + e);
        }
        try {
            c.add("X");
        } catch (Exception e) {
            System.out.println("add(): " + e);
        }
        try {
            c.addAll(c2);
        } catch (Exception e) {
            System.out.println("addAll(): " + e);
        }
        try {
            c.remove("C");
        } catch (Exception e) {
            System.out.println("remove(): " + e);
        }
        // The List.set() method modifies the value but
        // doesn't change the size of the data structure:
        try {
            list.set(0, "X");
        } catch (Exception e) {
            System.out.println("List.set(): " + e);
        }
    }

    public static void main(String[] args) {

        // Array.asList()会生成一个List,他基于大小的数组(final类型数组)、
        // 仅仅支持哪些不会改变数组的大小的操作。
        // 任何会引起对低层的数组结构的尺寸进行修改的方法会会产生一个
        // UnsupportedOperationException异常。
        List<String> list =
                Arrays.asList("A B C D E F G H I J K L".split(" "));

        // 拷贝一个新的数组对象赋值给new ArrayList()对象，底层数组大小可修改
        test("Modifiable Copy", new ArrayList<String>(list));

        // 底层数组大小可修改
        test("Arrays.asList()", list);

        // 底层数组大小可修改-> 将底层可修改的数组转换为不可修改的数组
        test("unmodifiableList()",
                Collections.unmodifiableList(
                        new ArrayList<String>(list)));
    }
} /* Output:
--- Modifiable Copy ---
--- Arrays.asList() ---
retainAll(): java.lang.UnsupportedOperationException
removeAll(): java.lang.UnsupportedOperationException
clear(): java.lang.UnsupportedOperationException
add(): java.lang.UnsupportedOperationException
addAll(): java.lang.UnsupportedOperationException
remove(): java.lang.UnsupportedOperationException
--- unmodifiableList() ---
retainAll(): java.lang.UnsupportedOperationException
removeAll(): java.lang.UnsupportedOperationException
clear(): java.lang.UnsupportedOperationException
add(): java.lang.UnsupportedOperationException
addAll(): java.lang.UnsupportedOperationException
remove(): java.lang.UnsupportedOperationException
List.set(): java.lang.UnsupportedOperationException
*///:~
