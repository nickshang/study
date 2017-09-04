package lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

/**
 * lambda�﷨��Ϥ
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

        // ʹ�� lambda ���ʽ�Լ���������(functional operation)
        players.forEach((player) -> System.out.print(player + "; "));


        // �� Java 8 ��ʹ��˫ð�Ų�����(double colon operator)
        players.forEach(System.out::println);

        // 1.1 ʹ�������ڲ������ name ���� players
        Arrays.sort(atp, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });


        // ������ ��lambda ���ʽ
        Comparator<String> s = (first,second) -> first.length() - second.length();
        players.sort( s );
        System.out.println( "###out:" + players );
        Arrays.sort( atp  , (first,second) -> first.length() - second.length() );

        // 1.1ʹ�������ڲ���
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        }).start();

        // 1.2ʹ�� lambda expression
        new Thread(() -> System.out.println("Hello world !")).start();

        // 2.1ʹ�������ڲ���
        Runnable race1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        };

        // 2.2ʹ�� lambda expression
        Runnable race2 = () -> System.out.println("Hello world !");

        // ֱ�ӵ��� run ����(û�����߳�Ŷ!)
        race1.run();
        race2.run();


        BiFunction<String,String,Integer> comp = (first ,second) -> Integer.compare(first.length(),second.length());


        // ʹ�÷������ã���
        Arrays.sort(atp,String::compareToIgnoreCase);

//        players.sort( comp );
    }


}
