package chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * VM OPTIONS: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * Created by Think on 2017/9/16.
 */
public class HeapOOM {
    static class  OOMObject {}

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        int i = 0;
        while(true){
            System.out.println(i++);
            list.add(new OOMObject());
        }
    }
}
