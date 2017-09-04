package thinkinjava.arrays;//: arrays/MultidimensionalPrimitiveArray.java
// Creating multidimensional arrays.

import java.util.*;

public class MultidimensionalPrimitiveArray {
    public static void main(String[] args) {
        int[][] a = {
                {1, 2, 3,},
                {4, 5, 6,},
        };
        System.out.println(Arrays.deepToString(a));

        int[][][] b = {
                {{1,2},{3,4}},
                {{1,2},{3,4}},
        };
        System.out.println(Arrays.deepToString(b));
    }
} /* Output:
[[1, 2, 3], [4, 5, 6]]
*///:~
