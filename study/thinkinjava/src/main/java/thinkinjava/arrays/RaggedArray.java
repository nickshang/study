package thinkinjava.arrays;//: arrays/RaggedArray.java

import java.util.*;

public class RaggedArray {

    // 粗糙数组 -> 数组中构造矩阵的每个向量都可以具有任意的长度

    public static void main(String[] args) {
        Random rand = new Random(47);
        // 3-D array with varied-length vectors:
        int[][][] a = new int[rand.nextInt(7)][][];
        for (int i = 0; i < a.length; i++) {
            a[i] = new int[rand.nextInt(5)][];                      //  二维长度
            for (int j = 0; j < a[i].length; j++){
                a[i][j] = new int[rand.nextInt(5)];                 //  三维长度
            }

        }
        System.out.println(Arrays.deepToString(a));
    }
} /* Output:
[[], [[0], [0], [0, 0, 0, 0]], [[], [0, 0], [0, 0]], [[0, 0, 0], [0], [0, 0, 0, 0]], [[0, 0, 0], [0, 0, 0], [0], []], [[0], [], [0]]]
*///:~
