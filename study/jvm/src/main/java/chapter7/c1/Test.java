package chapter7.c1;

/**
 * Created by Think on 2017/9/12.
 */
public class Test {
    /**
     * 只会输出"Super init!“,不会输出"subclass init！"
     * 对于静态字段，只有直接定义了这个字段的类才会初始化
     * 因此通过其子类引用父类中的定义静态字段。只会触发父类的初始化而不是触发子类的初始化。
     * 具体是否初始化子类，虚拟机没有定义，取决于具体虚拟机实现。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(SubClass.VALID);
    }
}
