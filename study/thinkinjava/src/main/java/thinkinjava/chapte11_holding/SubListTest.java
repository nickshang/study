package thinkinjava.chapte11_holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Think on 2016/5/18.
 */
public class SubListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        List<String> subList1 = list.subList(0,3);
        List<String> subList2 = Arrays.asList("3","5");

        System.out.println(list.containsAll(subList1));
        System.out.println(list.containsAll(subList2));

        System.out.println(list.retainAll(subList2) );

    }
}
