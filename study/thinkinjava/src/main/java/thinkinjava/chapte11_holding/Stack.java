package thinkinjava.chapte11_holding;

import java.util.LinkedList;

/**
 * Created by Think on 2016/5/18.
 */
public class Stack<E> {
    private LinkedList<E> storage = new LinkedList<>();

    public boolean add(E e){ return  storage.add(e);}

    public E pop(){ return storage.pop(); }

    public E peek(){ return  storage.peek(); }


}
