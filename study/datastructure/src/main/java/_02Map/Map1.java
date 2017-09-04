package _02Map;

import java.util.*;

/**
 * 实现一个只读Map
 */
public class Map1 extends AbstractMap<String,String> {

    private int size;

    public Map1(int i){
        if(i < 0){
            this.size = 0;
        }else if(i > DATA.length){
            this.size = DATA.length;
        }else
            this.size = i;
    }

    private  static  String[][]  DATA = {{"nick","yy"},{"jcoy","cs"},{"jocy1","cs1"}};

    // 创建Entry
    private static class  Entry implements Map.Entry<String,String>{

        private int index ;

        public Entry(int i){
            this.index = i;
        }

        @Override
        public String getKey() {
            return DATA[index][0];
        }

        @Override
        public String getValue() {
            return DATA[index][1];
        }

        @Override
        public String setValue(String value) {
            throw new UnsupportedOperationException();
        }
    }


    // 创建Set
    private class entrySet extends AbstractSet<Map.Entry<String,String>>{

        // 创建迭代器
        class Iter implements Iterator<Map.Entry<String, String>>{

            Entry entry = new Entry(-1);

            @Override
            public boolean hasNext() {
                return entry.index < size -1;
            }

            @Override
            public Map.Entry<String, String> next() {
                entry.index++;
                return entry;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        @Override
        public Iterator<Map.Entry<String, String>> iterator() {
            return new Iter();
        }

        @Override
        public int size() {
            return size;
        }
    }


    @Override
    public Set<Map.Entry<String, String>> entrySet() {
        return new entrySet();
    }

    public static void main(String[] args) {
        Map1 map = new Map1(2);
        for(Map.Entry<String,String> entry : map.entrySet()){
            System.out.println("key:" + entry.getKey());
            System.out.println("value:" + entry.getValue());
        }
    }
}
