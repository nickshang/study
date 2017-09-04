package thinkinjava.io;//: io/GZIPcompress.java
// {Args: GZIPcompress.java}

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 用GZIP进行简单压缩
 */
public class GZIPcompress {

  public static void main(String[] args)
  throws IOException {

    if(args.length == 0) {
      System.out.println(
        "Usage: \nGZIPcompress file\n" +
        "\tUses GZIP compression to compress " +
        "the file to test.gz");
      System.exit(1);
    }

//     字符流方式压缩
    BufferedReader in = new BufferedReader(
      new FileReader(args[0]));
    BufferedOutputStream out = new BufferedOutputStream(
      new GZIPOutputStream(
        new FileOutputStream("d:\\test.gz")));
    System.out.println("Writing file");
    int c;
    while((c = in.read()) != -1)
            out.write(c);

    in.close();
    out.close();

    // 字节流方式压缩
//    BufferedInputStream in = new BufferedInputStream(
//      new FileInputStream(new File(args[0])));
//    BufferedOutputStream out = new BufferedOutputStream(
//      new GZIPOutputStream(
//        new FileOutputStream("d:\\test.gz")));
//    System.out.println("Writing file");
//    byte[] b = new byte[1024];
//    int i = 0;
//    while( (i  = in.read(b,0,1024) ) > 0 ){
//      out.write(b,0,i);
//    }
//
//
//    in.close();
//    out.close();

    // 读取文件
    System.out.println("Reading file");
    BufferedReader in2 = new BufferedReader(
      new InputStreamReader(new GZIPInputStream(
        new FileInputStream("d:\\test.gz"))));
    String s;
    while((s = in2.readLine()) != null)
      System.out.println(s);
  }

} /* (Execute to see output) *///:~
