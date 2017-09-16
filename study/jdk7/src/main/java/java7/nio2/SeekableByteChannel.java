package java7.nio2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * SeekableByteChannel接口
 * 对文件字节通道进行定位获取
 * Think on 2016/6/24.
 */
public class SeekableByteChannel {

    public static void main(String[] args) throws UnsupportedEncodingException {

        // 创建路径
        Path logfile = Paths.get("D:\\log.log");

        // 缓冲器
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try {
            // 获取文件字节通道
            FileChannel channel = FileChannel.open(logfile, StandardOpenOption.READ);

            // 对文件字节通道进行定位获取
            channel.read(buffer,channel.size() -1000);

        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] b = buffer.array();
        String str= new String(b,"GBK");
        System.out.println(str);
    }
}
