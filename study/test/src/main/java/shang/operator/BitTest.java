package shang.operator;

/**
 * Created by Think on 2016/5/23.
 */
public class BitTest {
    public static void main(String[] args) {
//        A.  flag&=~2
//        B.  flag|=2
//        C.  flag^=2
//        D.  flag>>=2

        int flag = 5;
        System.out.println(flag&=~2);
        System.out.println("1:" + Integer.toBinaryString(flag));
        System.out.println(flag|=2);
        System.out.println("2:" + Integer.toBinaryString(flag));
        System.out.println(flag^=2);
        System.out.println("3:" + Integer.toBinaryString(flag));
        System.out.println(flag>>=2);
        System.out.println("4:" + Integer.toBinaryString(flag));

    }
}
