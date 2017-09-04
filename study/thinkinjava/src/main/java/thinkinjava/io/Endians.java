package thinkinjava.io;//: io/Endians.java
// Endian differences and data storage.

import java.nio.*;
import java.util.*;

import static  thinkinjava.net.mindview.util.Print.*;

/**
 *  通过字节存放模式设置来改变字符中的字节次序
 */
public class Endians {

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[12]);      // ByteBuffer存储字节12字节
        bb.asCharBuffer().put("中bcdef");                    // 存放6个char类型数据(一个char占两个字节)
        print(Arrays.toString(bb.array()));

        bb.rewind();
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        print(Arrays.toString(bb.array()));

        bb.rewind();
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        print(Arrays.toString(bb.array()));

        bb.clear();
        bb.rewind();
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.put( (byte)('1' << 10) );
        print(Arrays.toString(bb.array()));

    }
} /* Output:
[0, 97, 0, 98, 0, 99, 0, 100, 0, 101, 0, 102]
[0, 97, 0, 98, 0, 99, 0, 100, 0, 101, 0, 102]
[97, 0, 98, 0, 99, 0, 100, 0, 101, 0, 102, 0]
*///:~
