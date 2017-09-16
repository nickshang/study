package com.shang.util;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * 字符编码 总结
 * Think on 2016/6/27.
 */
public class CharsetUtil {

    public static void main(String[] args) throws IOException {

        String str = "中文";
        String _str = null;

        // 获取默认的字符集
        if (true) {

            // 获取默认的字符集
            String defaultCharset = Charset.defaultCharset().displayName();
            String encoding = System.getProperty("file.encoding");

            System.out.println(String.format("默认字符集:%s", defaultCharset));
            System.out.println(String.format("默认字符集:%s", encoding));

            // String字符编码转换
            _str = new String(str.getBytes("GBK"), "GBK");
            System.out.println(String.format("String字符编码转换:%s", _str));

            // 不同字符转换成字节区别
            System.out.println(String.format("String %s 长度:%d", "UTF-8", str.getBytes("UTF-8").length));
            System.out.println(String.format("String %s 长度:%d", "GBK", str.getBytes("GBK").length));
            System.out.println(String.format("String %s 长度:%d", "UTF-16", str.getBytes("UTF-16").length));
            System.out.println(String.format("String %s 长度:%d", "ISO-8859-1", str.getBytes("ISO-8859-1").length));

        }

        // 字符串 -> 字节数组 -> 缓冲字节器 （转换）
        if (true) {

            // (字符串 -> 字节数组)    中间通过CharsetEncoder   String ->  byte[]
            byte[] _byte = _str.getBytes("UTF-8");


            // (字节数组-> 缓冲字节器)    byte[] -> ByteBuffer
            ByteBuffer bb = ByteBuffer.wrap(_byte);

            // (缓冲字节-> 字节数组)    ByteBuffer -> byte[]
            _byte = bb.array();

            // (字节数组-> 字符)      中间通过CharsetDecoder   byte[]- > String
            _str = new String(_byte, "UTF-8");
        }


        //  字符串 -> 缓冲字节器 （转换）
        if (true) {
            ByteBuffer bb2 = Charset.forName("utf-8").encode(_str);
            CharBuffer cb = Charset.forName("utf-8").decode(bb2);

            // 字符缓冲器 -》 字符串
            _str = cb.toString();
            System.out.println(_str);
        }

        // 文件 -> Reader -> 字符串
        if (true) {
            FileInputStream fis = new FileInputStream(new File("C:\\Users\\Think\\Desktop\\算法.txt"));
            InputStreamReader is = new InputStreamReader(fis, "UTF-8");
            BufferedReader br =  new BufferedReader( is );

            String s = null;
            while( (s = br.readLine() ) != null ){
                System.out.println(s);
            }

            br.close();
            fis.close();
            is.close();
        }


        // 文件 -> InputStream -> char[] -> 字符串
        if (true) {
            FileInputStream fis = new FileInputStream(new File("C:\\Users\\Think\\Desktop\\算法.txt"));
            ByteBuffer bb = ByteBuffer.allocate(1024*3);

            // 读取字节
            byte[] c = new byte[1024];
            int i = 0;
            while( ( i = fis.read(c,0,c.length)) > 0){
                System.out.println("--------------");
                bb.put(c,0,i);
            }

            // 重新定位指针
            bb.flip();
            _str = Charset.forName("UTF-8").decode(bb).toString();

            System.out.println("sL:"+ _str);

            fis.close();
        }


    }
}
