package lambda.lx;

import java.util.ArrayList;
import java.util.List;

/**
 * lambda表达式学习 -> lambda捕获变量
 * Think on 2016/6/30.
 */
public class LambdaTest {
    public static void main(String[] args) {
        String[] names = {"perter","paul","mary"};
        List<Runnable> runners = new ArrayList<>();

        // 写法1
//        for (String name : names){
//            runners.add( () -> System.out.println(name) ) ;
//        }

        // 写法2
        for (int i = 0 ; i < names.length; i++){
            String _name = names[i];
            runners.add( () -> System.out.println(_name) ) ;
        }

        for (Runnable r :  runners) {
            r.run();
        }
    }
}
