package thinkinjava.io;//: io/GetChannel.java
// Getting channels from streams

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileInputStream、FileOutpStream、RandomAccessFile面向字节操作流，与低层的nio性质一致。
 * Reader,Writer字符模式类，不能用户产生通道，但是java.nio.channels.Channels类提供了实用方法，用以在通道中产生Reader和Wrtier.
 * 演示FileInputStream,FileOutpStream,RandomAccessFile，用以产生可写的，可读可写的及可读的通道。
 */
public class GetChannel {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws Exception {
        String path = GetChannel.class.getResource("").toString();
        path = path.substring(path.indexOf(":") + 2);
        System.out.println("路径:"+path);

        // Write a file:
        // 从FileOutputStream产生可写的管道，然后写入数据
        FileChannel fc = new FileOutputStream(path + "data2.txt").getChannel();
        // 唯一与管道交互的缓存器ByteBuffer
        fc.write(ByteBuffer.wrap("Some text ".getBytes()));
        fc.close();

        // Add to the end of the file:
        // 从RandomAccessFile中获取可读可写的管道，然后写入数据为管道最后
        fc = new RandomAccessFile( path + "data2.txt", "rw").getChannel();
        fc.position(fc.size()); // Move to the end
        fc.write(ByteBuffer.wrap("Some more".getBytes()));
        fc.close();


        // Read the file:
        // 从FileInputStream中获取可读的管道，然后写入数据为管道最后
        fc = new FileInputStream(path + "data2.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);

        // 在一系列通道读取或放置 操作之后，调用此方法为一系列通道写入或相对获取 操作做好准备。
        // 当将数据从一个地方传输到另一个地方时，经常将此方法与 compact 方法一起使用。
        buff.flip();

        // hasRemaining() 告知在当前位置和限制之间是否有元素。
        while (buff.hasRemaining())
            System.out.print((char) buff.get());
    }
} /* Output:
Some text Some more
*///:~
