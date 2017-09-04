package thinkinjava.chapte13_string;

/**
 * Created by Think on 2016/5/25.
 */

class Building {}
class House extends Building {}

public class ClassCasts {
    public static void main(String[] args) {
        Building b = new House();
        Class<House> houseType = House.class;
        House h = houseType.cast(b);             //  将实例：b强制转换成此 House 对象所
        h = (House)b; // ... or just do this.    //  另外一种写法
    }
}