package lambda.lx;

/**
 *
 * 编写一个静态方法andThen，他接收了Runnable实例作为参数。并放回一个分别运行这两个实例的Runnable对象。
 * 2016/6/30.
 */
public class AndThen {

    public static void andThen(Runnable a,Runnable b){
        a.run();
        b.run();
    }

    public static void main(String[] args) {
        andThen( ()-> {
            System.out.println("----A-----");
        }, ()-> {
            System.out.println("----B-----");
        });
    }
}
