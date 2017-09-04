package thinkinjava.chapte11_holding;

import thinkinjava.typeinfo.pets.Pet;
import thinkinjava.typeinfo.pets.Pets;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by Think on 2016/5/18.
 */
public class ListIteration {
    public static void main(String[] args) {
        List<Pet> pets = Pets.arrayList(8);
        ListIterator<Pet> it = pets.listIterator();
        while(it.hasNext())
            System.out.print(it.next() + ", " + it.nextIndex() +
                    ", " + it.previousIndex() + "; ");
        System.out.println();
        // Backwards:
        while(it.hasPrevious())
            System.out.print(it.previous().id() + " ");
        System.out.println();
        System.out.println(pets);
        it = pets.listIterator(6);    // 获取从序号7开始的元素
        while(it.hasNext()) {         // 修改从7开始的元素
            it.next();
            it.set(Pets.randomPet());
        }
        System.out.println(pets);
    }
}