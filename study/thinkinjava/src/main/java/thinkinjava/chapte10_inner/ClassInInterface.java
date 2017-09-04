package thinkinjava.chapte10_inner;

/**
 * Created by Think on 2016/5/18.
 */
public interface ClassInInterface {
    void howdy();
    class Test implements ClassInInterface {
        public void howdy() {
            System.out.println("Howdy!");
        }
        public static void main(String[] args) {
            new Test().howdy();
        }
    }
}