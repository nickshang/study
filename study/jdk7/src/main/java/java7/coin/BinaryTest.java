package java7.coin;

/**
 * �����Ʊ�ʾ��
 */
public class BinaryTest {

    public static void main(String[] args) {

        // 0b ������
        // 0o �˽���
        // 0x ʮ������

        int x = 0b11;
        System.out.println(x);  // ������ -> 3

        x = 00011;
        System.out.println(x);  // �˽��� -> 9

        x = 0x11;
        System.out.println(x);  // ʮ������ -> 17

        // �»��������ʾ��
        long another = 2_147_483;
        int bitPatter = 0b1111_1111;

    }
}
