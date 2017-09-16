package chapter7.c2;


/**
 *
 * Created by Think on 2017/9/12.
 */
public class Test2 {

    /**
     * 没有输出const class init
     * 因为虽然在Java源码中引用了ConstClass类中的常量HELLOWORLD,但是在编译节点通过常量传播优化，
     * 已将将"hello word"存储在Test2类的常量池中。以后Test2对d对ConstClasss.HELLOWORLD是引用，实际都转化为
     * Test2类只身常量池的引用了。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(ConstClass.HELLOWORLD);
    }
}
