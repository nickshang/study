package thinkinjava.chapte13_string;

/**
 * Created by Think on 2016/5/25.
 */
import java.util.*;

public class InfiniteRecursion {
    public String toString() {
//        return " InfiniteRecursion address: " + super.toString() + "\n";
          return " InfiniteRecursion address: " + this.toString() + "\n";
        // ʹ��this��ӡ������Ϣ���ᵼ�µݹ����
        // Ӧ��ʹ��Object.toString()�������˴�ʹ��super.toString()����
    }
    public static void main(String[] args) {
        List<InfiniteRecursion> v =
                new ArrayList<InfiniteRecursion>();
        for(int i = 0; i < 10; i++)
            v.add(new InfiniteRecursion());
        System.out.println(v);
    }
}