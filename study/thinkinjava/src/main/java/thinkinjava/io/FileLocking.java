package thinkinjava.io;//: io/FileLocking.java

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * 文件加锁实例
 */
public class FileLocking {
    public static void main(String[] args) throws Exception {
        String path = GetChannel.class.getResource("").toString();
        path = path.substring(path.indexOf(":") + 2);
        System.out.println("路径:"+path);

        FileOutputStream fos = new FileOutputStream("D:\\log1.log");
        FileLock fl = fos.getChannel().lock();
        if (fl != null) {
            System.out.println("Locked File");
            TimeUnit.SECONDS.sleep(10000);
            fl.release();
            System.out.println("Released Lock");
        }
        fos.close();
    }
} /* Output:
Locked File
Released Lock
*///:~
