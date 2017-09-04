package thinkinjava.arrays;//: arrays/ParameterizedArrayType.java

class ClassParameter<T> {
    public T[] f(T[] arg) {
        return arg;
    }
}

// 使用参数化方法二不使用参数化类的方便之处在于：不比为需要应用每种不同类型都使用参数化去实例化这个类，
// 并且可以将其定义为静态的。
class MethodParameter {
    public static <T> T[] f(T[] arg) {
        return arg;
    }
}

// 参数化数组本身的类型
public class ParameterizedArrayType {
    public static void main(String[] args) {
        Integer[] ints = {1, 2, 3, 4, 5};
        Double[] doubles = {1.1, 2.2, 3.3, 4.4, 5.5};
        Integer[] ints2 =
                new ClassParameter<Integer>().f(ints);
        Double[] doubles2 =
                new ClassParameter<Double>().f(doubles);
        ints2 = MethodParameter.f(ints);
        doubles2 = MethodParameter.f(doubles);
    }
} ///:~
