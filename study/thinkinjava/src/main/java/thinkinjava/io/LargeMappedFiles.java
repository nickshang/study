package thinkinjava.io;//: io/LargeMappedFiles.java
// Creating a very large file using mapping.
// {RunByHand}

import java.nio.*;
import java.nio.channels.*;
import java.io.*;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * 内存文件映射
 */
public class LargeMappedFiles {
    static int length = 0x8FFFFFF; // 128 MB

    public static void main(String[] args) throws Exception {
        MappedByteBuffer out =
                new RandomAccessFile("E:\\work\\soft\\Linux\\rhel-server-5.7-i386-dvd.iso", "rw").getChannel()
                        .map(FileChannel.MapMode.READ_WRITE, 0, length);
        for (int i = 0; i < length; i++)
            out.put((byte) 'x');
        print("Finished writing");
        for (int i = length / 2; i < length / 2 + 6; i++)
            printnb((char) out.get(i));
    }
} ///:~
