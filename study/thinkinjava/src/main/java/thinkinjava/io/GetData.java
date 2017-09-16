package thinkinjava.io;//: io/GetData.java
// Getting different representations from a ByteBuffer

import java.nio.*;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

public class GetData {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        // Allocation automatically zeroes the ByteBuffer:
        int i = 0;
        while (i++ < bb.limit()) {   //  bb.limit() -》 返回缓冲器限制
            byte b = bb.get();
            System.out.println(b);
            if (b != 0)
                print("nonzero");
        }
        print("i = " + i);
        bb.rewind();


        // Store and read a char array:
        // 缓冲器保存字符,并输出
        bb.asCharBuffer().put("Howdy!");
        char c;
        while ((c = bb.getChar()) != 0)
            printnb(c + " ");
        print();
        bb.rewind();

//        byte char short int  long float double
//        8     ·6     16   32    64  32    64

        // Store and read a short:
        // 缓冲器存储short,并输出
        bb.asShortBuffer().put((short) 471142);
        print(bb.getShort());
        bb.rewind();


        // Store and read an int:
        // 缓冲器保存int,并输出
        bb.asIntBuffer().put(99471142);
        print(bb.getInt());
        bb.rewind();


        // Store and read a long:
        // 缓冲器保存long,并输出
        bb.asLongBuffer().put(99471142);
        print(bb.getLong());
        bb.rewind();

        // Store and read a float:
        // 缓冲器保存float,并输出
        bb.asFloatBuffer().put(99471142);
        print(bb.getFloat());
        bb.rewind();


        // Store and read a double:
        // 缓冲器保存float,并输出
        bb.asDoubleBuffer().put(99471142);
        print(bb.getDouble());
        bb.rewind();
    }
} /* Output:
i = 1025
H o w d y !
12390
99471142
99471142
9.9471144E7
9.9471142E7
*///:~
