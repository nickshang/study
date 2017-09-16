package thinkinjava.io;//: io/UsingBuffers.java

import java.nio.*;

import static net.mindview.util.Print.print;

/**
 * 演示一个简单的算法（交换相邻字符），
 * 以对CharBuffer中的字符进行编码(scramble)和译吗(unscramble)
 */
public class UsingBuffers {
    private static void symmetricScramble(CharBuffer buffer) {
        while (buffer.hasRemaining()) {         // 判断position和limit之间的元素
            buffer.mark();                      // 标记
            char c1 = buffer.get();             // 获取当前位置的数据 position向后移动
            char c2 = buffer.get();             // 获取当前位置的数据 position向后移动
            buffer.reset();                     // posititon向前移动
            buffer.put(c2).put(c1);             // 放置数据[调换位置]
        }
    }

    public static void main(String[] args) {
        char[] data = "UsingBuffers".toCharArray();
        ByteBuffer bb = ByteBuffer.allocate(data.length * 2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(data);

        print(cb.rewind());
        symmetricScramble(cb);

        print(cb.rewind());
        symmetricScramble(cb);

        print(cb.rewind());
    }
} /* Output:
UsingBuffers
sUniBgfuefsr
UsingBuffers
*///:~
