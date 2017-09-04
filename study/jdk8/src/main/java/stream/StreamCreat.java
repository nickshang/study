package stream;

import java.util.stream.Collectors;

/**
 * Created by Think on 2017/5/12.
 */
public class StreamCreat {

    public static void main(String[] args) {
        String[] cloums = SysConstant.DAY_LOAD_CURVE_96COLUMN;
        java.util.stream.Stream<String> w = java.util.stream.Stream.of(cloums);

        int index = w.collect(Collectors.toList()).indexOf("T1300");
        w = java.util.stream.Stream.of(cloums).skip(index);
        String result = w.collect(Collectors.joining(" = null,"));
        System.out.println(result);

    }
}
