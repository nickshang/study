package lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

/**
 * lambda语法熟悉
 * Think on 2016/6/28.
 */
public class LambdaTest {


    public static void main(String[] args) {
//        String[] ar = {"1","12","123"};
//        Arrays.sort(ar,(first,second)_-> Integer.compare(first.length,second.length));


        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);

        // 使用 lambda 表达式以及函数操作(functional operation)
        players.forEach((player) -> System.out.print(player + "; "));


        // 在 Java 8 中使用双冒号操作符(double colon operator)
        players.forEach(System.out::println);

        // 1.1 使用匿名内部类根据 name 排序 players
        Arrays.sort(atp, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });


        // 匿名类 用lambda 表达式
        Comparator<String> s = (first,second) -> first.length() - second.length();
        players.sort( s );
        System.out.println( "###out:" + players );
        Arrays.sort( atp  , (first,second) -> first.length() - second.length() );

        // 1.1使用匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        }).start();

        // 1.2使用 lambda expression
        new Thread(() -> System.out.println("Hello world !")).start();

        // 2.1使用匿名内部类
        Runnable race1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        };

        // 2.2使用 lambda expression
        Runnable race2 = () -> System.out.println("Hello world !");

        // 直接调用 run 方法(没开新线程哦!)
        race1.run();
        race2.run();


        BiFunction<String,String,Integer> comp = (first ,second) -> Integer.compare(first.length(),second.length());


        // 使用方法引用，对
        Arrays.sort(atp,String::compareToIgnoreCase);

//        players.sort( comp );
    }


}
