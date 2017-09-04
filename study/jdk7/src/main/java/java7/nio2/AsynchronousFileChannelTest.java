package java7.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * �첽IO-����
 * Think on 2016/6/24.
 */
public class AsynchronousFileChannelTest {

    public static void main(String[] args) {

        // ����·��
        Path file = Paths.get("E:\\work\\soft\\Linux\\rhel-server-5.7-i386-dvd.iso");
        try {

            // �첽���ļ�
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);

            // ��ȡ100 000�ֽ�
            ByteBuffer buffer = ByteBuffer.allocate(1000_000);
            Future<Integer> result= channel.read(buffer,0);

            while(!result.isDone()){
                // �����߳�δ��ɵ�����£�����������
                // do something
                System.out.println("do something ......");
            }

            // ��ȡ���
            Integer byteRead = result.get();
            System.out.println( "Byte read [" +  byteRead+ "]");

        } catch (IOException | ExecutionException |InterruptedException e) {
            e.printStackTrace();
        }
    }
}
