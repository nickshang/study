package thinkinjava.io;//: io/FileLocking.java

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

public class FileLocking2 {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("D:\\log1.log");
        FileLock fl = fos.getChannel().lock();
        if (fl != null) {
            System.out.println("Locked File");
            TimeUnit.SECONDS.sleep(100000);
            fl.release();
            System.out.println("Released Lock");
        }
        fos.close();
    }
} /* Output:
Locked File
Released Lock
*///:~
