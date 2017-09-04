package thinkinjava.io;//: io/ZipCompress.java
// Uses Zip compression to compress any
// number of files given on the command line.
// {Args: ZipCompress.java}

import java.util.zip.*;
import java.io.*;
import java.util.*;

import static  thinkinjava.net.mindview.util.Print.*;

public class ZipCompress {
    public static void main(String[] args)
            throws IOException {
        String s = ZipCompress.class.getResource("").getPath();
        System.out.println(s);

        // 文件输出流
        FileOutputStream f = new FileOutputStream("d:\\test.zip");

        // 检查文件输出流
        CheckedOutputStream csum =
                new CheckedOutputStream(f, new Adler32());

        // ZIP文件输出流
        ZipOutputStream zos = new ZipOutputStream(csum);

        // 缓冲输出流
        BufferedOutputStream out =
                new BufferedOutputStream(zos);


        zos.setComment("A test of Java Zipping");
        // No corresponding getComment(), though.
        for (String arg : args) {
            print("Writing file " + arg);

            // 缓冲输入流
            BufferedReader in =
                    new BufferedReader(new FileReader(arg));

            // 对于要加入压缩档案的文件，都必须调用putNextEntry(),将其传递给一个ZipEntry对象
            // ZipEntry对象包含了一个功能很广泛的接口，允许你获取和设置Zip文件特定数据项所有可利用的数据：名字
            // 压缩的和为压缩的文件大小、日期、CRC校验和，额外字段数据、注释、压缩方法以及一个目录入口等。
            zos.putNextEntry(new ZipEntry(arg));
            int c;
            while ((c = in.read()) != -1)
                out.write(c);
            in.close();
            out.flush();
        }
        out.close();
        // Checksum valid only after the file has been closed!
        print("Checksum: " + csum.getChecksum().getValue());


        // Now extract the files:
        print("Reading file");
        // 读取文件
        FileInputStream fi = new FileInputStream("d:\\test.zip");

        // 读取检查流
        CheckedInputStream csumi =
                new CheckedInputStream(fi, new Adler32());

        // ZIP文件输入流
        ZipInputStream in2 = new ZipInputStream(csumi);

        // 缓冲输出流
        BufferedInputStream bis = new BufferedInputStream(in2);

        //
        ZipEntry ze;
        while ((ze = in2.getNextEntry()) != null) {
            print("Reading file " + ze);
            int x;
            while ((x = bis.read()) != -1)
                System.out.write(x);
        }
        if (args.length == 1)
            print("Checksum: " + csumi.getChecksum().getValue());
        bis.close();


        // 另外一种读取方式
        // Alternative way to open and read Zip files:
        ZipFile zf = new ZipFile("test.zip");
        Enumeration e = zf.entries();
        while (e.hasMoreElements()) {
            ZipEntry ze2 = (ZipEntry) e.nextElement();
            print("File: " + ze2);
            // ... and extract the data as before
        }
    /* if(args.length == 1) */
    }
} /* (Execute to see output) *///:~
