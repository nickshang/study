package lambda;

/**
 * lanbda表达式 -》变量作用域笔记
 * Think on 2016/6/29.
 */
public class ScopeTest {

    public static void main(String[] args) {
        repeatMessage("hello",100);
    }

    /**
     * 变量作用域访问 -》 在lambda闭包的方法内或者类中访问其他的变量
     * 一个lambda表达式三个部分：
     * 1. 一段代码
     * 2. 参数
     * 3. 自由变量的值：这里的“自由”值的是那些不是参数并且没有代码中定义的变量
     *
     * lambda表达式已经捕获。
     * （这是一个技术实现的细节。例如,你可以将一个lambda表达是转换为一个只含一个方法的对象(参数final)，这样自由变量的值就回呗复制到
     * 该对象实例变量中）
     *
     * 含有自由变量的代码被称为“闭包(closure)”。如果有人得意洋洋地宣传他们语言有闭包。
     * Java中,lambda表达是就是闭包。内部类一致就是闭包.Java 8中为闭包赋予更吸引的的语法。
     *
     * 为了确保被捕获的值是被良好定义的，需要这遵守一个重要的约束。在lambda表达中，被引用的的变量的值不可变改变。
     * 原因：改变lambda表达式的变量不是线程安全的。
     *
     * 内部类也会捕获闭和作用域中的值。JAVA8之前，内部类只允许访问final的局部变量，为了适应lambda表示式。
     * 这样规则也被放宽了。一个内部类可以访问任何有效的final局部变量-即任何值不会发生变化的变量。
     *
     * 不要指望编译器会捕获所有的并发访问错误。
     * 不可变的约束只能作用在局部变量上。
     * 如果matches是一个实例变量或者某个闭合类的静态变量，那么不会有任何错误被报告出来，即使结果同样未定义。
     *
     * @param text 文本
     * @param count 次数
     */
    public static void repeatMessage(String text, int count) {
        Runnable r = () -> {
            for (int i = 0; i < count; i++) {

                // lambda表达式(捕获)作用域的变量
                System.out.println("text:" + text);
                System.out.println("count:" + i);

                // 为了确保被捕获的值是被良好定义的，需要这遵守一个重要的约束。
                // 在lambda表达中，被引用的的变量的值不可变改变
                // count-- ;    // 错误  参数被定义为final

            }
        };
        new Thread(r).start();
    }
}
