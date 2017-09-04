package lambda.lx;

import java.util.ArrayList;
import java.util.List;

/**
 * lambda���ʽѧϰ -> lambda�������
 * Think on 2016/6/30.
 */
public class LambdaTest {
    public static void main(String[] args) {
        String[] names = {"perter","paul","mary"};
        List<Runnable> runners = new ArrayList<>();

        // д��1
//        for (String name : names){
//            runners.add( () -> System.out.println(name) ) ;
//        }

        // д��2
        for (int i = 0 ; i < names.length; i++){
            String _name = names[i];
            runners.add( () -> System.out.println(_name) ) ;
        }

        for (Runnable r :  runners) {
            r.run();
        }
    }
}
