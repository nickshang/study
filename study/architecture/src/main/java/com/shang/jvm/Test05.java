package com.shang.jvm;

/**
 * Created by Think on 2017/11/7.
 */
import java.util.HashMap;
import java.util.Map;

public class Test05 {

    public static void main(String[] args) {

        //参数：-Xmx30M -Xms30M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1000*1024
        Map<Integer, byte[]> m = new HashMap<Integer, byte[]>();
        //每次创建1M数据，因为设置了PretenureSizeThreshold=1000*1024，该数据是小于1M(1024*1024)的，所以产生的数据是不能放在新生代的，只能存储在老年区中
        for(int i=0; i< 5; i++){
            byte[] b = new byte[1024*1024];
            m.put(i, b);
        }
    }
}