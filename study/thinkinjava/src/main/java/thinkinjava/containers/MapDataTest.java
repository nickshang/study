package thinkinjava.containers;//: containers/MapDataTest.java

import thinkinjava.net.mindview.util.Generator;
import thinkinjava.net.mindview.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

class Letters implements Generator<Pair<Integer, String>>,
        Iterable<Integer> {
    private int size = 9;
    private int number = 1;
    private char letter = 'A';

    public Pair<Integer, String> next() {
        return new Pair<Integer, String>(
                number++, "" + letter++);
    }

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            public Integer next() {
                return number++;
            }

            public boolean hasNext() {
                return number < size;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

public class MapDataTest {
    public static void main(String[] args) {
//        // Pair Generator:
//        print(MapData.map(new Letters(), 11));
//        // Two separate generators:
//        print(MapData.map(new CountingGenerator.Character(),
//                new RandomGenerator.String(3), 8));
//        // A key Generator and a single value:
//        print(MapData.map(new CountingGenerator.Character(),
//                "Value", 6));
//        // An Iterable and a value Generator:
//        print(MapData.map(new Letters(),
//                new RandomGenerator.String(3)));
//        // An Iterable and a single value:
//        print(MapData.map(new Letters(), "Pop"));

        Collection<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(11);
        list.add(12);

        System.out.println(Collections.max(list));
        System.out.println(Collections.min(list));
    }
} /* Output:
{1=A, 2=B, 3=C, 4=D, 5=E, 6=F, 7=G, 8=H, 9=I, 10=J, 11=K}
{a=YNz, b=brn, c=yGc, d=FOW, e=ZnT, f=cQr, g=Gse, h=GZM}
{a=Value, b=Value, c=Value, d=Value, e=Value, f=Value}
{1=mJM, 2=RoE, 3=suE, 4=cUO, 5=neO, 6=EdL, 7=smw, 8=HLG}
{1=Pop, 2=Pop, 3=Pop, 4=Pop, 5=Pop, 6=Pop, 7=Pop, 8=Pop}
*///:~
