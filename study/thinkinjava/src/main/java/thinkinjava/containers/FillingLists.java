package thinkinjava.containers;//: containers/FillingLists.java
// The Collections.fill() & Collections.nCopies() methods.

import java.util.*;

class StringAddress {
    private String s;

    public StringAddress(String s) {
        this.s = s;
    }

    public String toString() {
        return super.toString() + " " + s;
    }
}

/**
 * 填充数组
 */
public class FillingLists {
    public static void main(String[] args) {
        List<StringAddress> list = new ArrayList<StringAddress>(
                Collections.nCopies(4, new StringAddress("Hello")));    // 填充ArrayList
        System.out.println(list);
        Collections.fill(list, new StringAddress("World!"));            // 填充ArrayList
        System.out.println(list);
    }
} /* Output: (Sample)
[StringAddress@82ba41 Hello, StringAddress@82ba41 Hello, StringAddress@82ba41 Hello, StringAddress@82ba41 Hello]
[StringAddress@923e30 World!, StringAddress@923e30 World!, StringAddress@923e30 World!, StringAddress@923e30 World!]
*///:~
