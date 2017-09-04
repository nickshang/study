package thinkinjava.chapte11_holding;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Think on 2016/5/19.
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("1","1_");
        map.put("2","2_");
        map.put("3","3_");
        map.put("4","4_");
        map.put("5","5_");
        map.put("6","6_");

        Collection<String> coll = map.values();
        coll.remove("1_");

        System.out.println(map);

    }
}
