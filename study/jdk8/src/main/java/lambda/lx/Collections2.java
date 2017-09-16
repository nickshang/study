package lambda.lx;


import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 定义Collection的子接口
 * Think on 2016/6/30.
 */
public interface Collections2<E> extends Collection<E> {
    default void forEachIf(Consumer<T> action, Predicate fifter){

    }
}

class T {

}