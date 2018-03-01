package com.shang.jvm;

/**
 * Created by Think on 2017/11/7.
 */
import java.util.HashMap;
import java.util.Map;

public class Test06 {

    public static void main(String[] args) {

        //参数：-Xmx30M -Xms30M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1000
        Map<Integer, byte[]> m = new HashMap<Integer, byte[]>();

        for(int i=0; i< 5*1024; i++){
            byte[] b = new byte[1024];//每次产生1K数据
            m.put(i, b);
        }
    }
}