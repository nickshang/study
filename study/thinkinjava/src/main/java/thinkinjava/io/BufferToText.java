package thinkinjava.io;//: io/BufferToText.java
// Converting text to and from ByteBuffers

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class BufferToText {
  private static final int BSIZE = 1024;

  public static void main(String[] args) throws Exception {

    String path = BufferToText.class.getResource("").toString();
    path = path.substring(path.indexOf(":") + 2);
    System.out.println("路径:"+path);

    // 写入文件
    FileChannel fc =
      new FileOutputStream(path + "data2.txt").getChannel();
    fc.write(ByteBuffer.wrap("Some text".getBytes()));
    fc.close();
    System.out.println("写入文件:" + path + "data2.txt" );

    // 读取文件
    fc = new FileInputStream(path + "data2.txt").getChannel();
    ByteBuffer buff = ByteBuffer.allocate(BSIZE);
    fc.read(buff);
    buff.flip();
    // Doesn't work:
    System.out.println("读取文件:" + buff.asCharBuffer());


    // Decode using this system's default Charset:
    // 使用系统默认的编码对缓冲器进行编码
    buff.rewind();  // 重绕此缓冲区。将位置设置为 0 并丢弃标记。
    String encoding = System.getProperty("file.encoding");
    //
    System.out.println("Decoded using " + encoding + ": "
      + Charset.forName(encoding).decode(buff));


    // Or, we could encode with something that will print:
    // 使用UTF-16BE进行编码
    fc = new FileOutputStream(path + "data2.txt").getChannel();
    fc.write(ByteBuffer.wrap(
      "Some text".getBytes("UTF-16BE")));
    fc.close();

    // Now try reading again:
    // 重新读取文件
    fc = new FileInputStream(path + "data2.txt").getChannel();
    buff.clear();
    fc.read(buff);
    buff.flip();
    System.out.println(buff.asCharBuffer());

    // Use a CharBuffer to write through:
    // 使用CharBuffer进行数据写入
    fc = new FileOutputStream(path + "data2.txt").getChannel();
    buff = ByteBuffer.allocate(24); // More than needed
    buff.asCharBuffer().put("Some text");
    fc.write(buff);
    fc.close();

    // Read and display:
    // 读取并显示
    fc = new FileInputStream(path + "data2.txt").getChannel();
    buff.clear();
    fc.read(buff);
    buff.flip();
    System.out.println(buff.asCharBuffer());


    // 文件中文写入并显示
    fc = new FileOutputStream(path + "data2.txt").getChannel();
    //buff = ByteBuffer.allocate(BSIZE);
    //buff.put(" 中华人民共和国 ".getBytes("UTF-8"));
    fc.write(  Charset.forName("GBK").encode(" china 中文  瑞士 人民共和国"));
    fc.close();

    fc = new FileInputStream(path + "data2.txt").getChannel();
    fc.read(buff);
    buff.flip();
    fc.close();
    System.out.println( Charset.forName("GBK").decode(buff));
//    System.out.println(new String(buff.array(),"GBK"));

  }
} /* Output:
????
Decoded using Cp1252: Some text
Some text
Some text
*///:~
