package thinkinjava.chapte13_string;

/**
 * Created by Think on 2016/5/25.
 */
public class Immutable {
    static {
        System.out.println("-------------init --------------");
    }
    public static String upcase(String s) {
        return s.toUpperCase();
    }



}