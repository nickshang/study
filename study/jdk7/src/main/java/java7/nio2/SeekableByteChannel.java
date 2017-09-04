package java7.nio2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * SeekableByteChannel�ӿ�
 * ���ļ��ֽ�ͨ�����ж�λ��ȡ
 * Think on 2016/6/24.
 */
public class SeekableByteChannel {

    public static void main(String[] args) throws UnsupportedEncodingException {

        // ����·��
        Path logfile = Paths.get("D:\\log.log");

        // ������
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try {
            // ��ȡ�ļ��ֽ�ͨ��
            FileChannel channel = FileChannel.open(logfile, StandardOpenOption.READ);

            // ���ļ��ֽ�ͨ�����ж�λ��ȡ
            channel.read(buffer,channel.size() -1000);

        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] b = buffer.array();
        String str= new String(b,"GBK");
        System.out.println(str);
    }
}
