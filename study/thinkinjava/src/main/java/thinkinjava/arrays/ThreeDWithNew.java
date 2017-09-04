package thinkinjava.arrays;//: arrays/ThreeDWithNew.java

import java.util.*;

public class ThreeDWithNew {
    public static void main(String[] args) {
        // 3-D array with fixed length:
        int[][][] a = new int[2][2][4];
        System.out.println(Arrays.deepToString(a));

        int b =  11222;
        int c =  11222;
        System.out.println(b == c);

        Integer d =  1;
        Integer e =  1;
        System.out.println(d == e);

        Integer integer3 = 300;
        Integer integer4 = 300;

        if (integer3 == integer4)
            System.out.println("integer3 == integer4");
        else
            System.out.println("integer3 != integer4");

        integer3 = new Integer(1);
        integer4 = new Integer(1);

        if (integer3 == integer4)
            System.out.println("integer3 == integer4");
        else
            System.out.println("integer3 != integer4"); // 输出



    }



} /* Output:
[[[0, 0, 0, 0], [0, 0, 0, 0]], [[0, 0, 0, 0], [0, 0, 0, 0]]]
*///:~
