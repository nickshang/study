package thinkinjava.io;//: io/ChannelCopy.java
// Copying a file using channels and buffers
// {Args: ChannelCopy.java test.txt}

import java.nio.*;
import java.nio.channels.*;
import java.io.*;

public class ChannelCopy {
    private static final int BSIZE = 2024;

    public static void main(String[] args) throws Exception {

        String[] s = new String[2];
        s[0] = "D:\\test.css";
        s[1] = "D:\\test.scss";

        FileChannel
                in = new FileInputStream(s[0]).getChannel(),
                out = new FileOutputStream(s[1]).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);

        // 一旦调用read()来告知FileChannel向ByteBuffer存储字节，就必须调用缓冲器上的的flip()
        // 让他做好人别人读取字节的准备。
        while (in.read(buffer) != -1) {
            buffer.flip(); // Prepare for writing
            out.write(buffer);
            buffer.clear();  // Prepare for reading
        }

        //
    }
} ///:~
