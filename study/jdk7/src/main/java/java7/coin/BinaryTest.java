package java7.coin;

/**
 * 二进制表示法
 */
public class BinaryTest {

    public static void main(String[] args) {

        // 0b 二进制
        // 0o 八进制
        // 0x 十六进制

        int x = 0b11;
        System.out.println(x);  // 二进制 -> 3

        x = 00011;
        System.out.println(x);  // 八进制 -> 9

        x = 0x11;
        System.out.println(x);  // 十六进制 -> 17

        // 下划线数组表示法
        long another = 2_147_483;
        int bitPatter = 0b1111_1111;

    }
}
