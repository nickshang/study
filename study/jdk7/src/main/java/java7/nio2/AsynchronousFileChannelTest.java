package java7.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 异步IO-将来
 * Think on 2016/6/24.
 */
public class AsynchronousFileChannelTest {

    public static void main(String[] args) {

        // 创建路径
        Path file = Paths.get("E:\\work\\soft\\Linux\\rhel-server-5.7-i386-dvd.iso");
        try {

            // 异步打开文件
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);

            // 读取100 000字节
            ByteBuffer buffer = ByteBuffer.allocate(1000_000);
            Future<Integer> result= channel.read(buffer,0);

            while(!result.isDone()){
                // 在主线程未完成的情况下，见其他任务
                // do something
                System.out.println("do something ......");
            }

            // 获取结果
            Integer byteRead = result.get();
            System.out.println( "Byte read [" +  byteRead+ "]");

        } catch (IOException | ExecutionException |InterruptedException e) {
            e.printStackTrace();
        }
    }
}
