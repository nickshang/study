//: net/mindview/util/Tuple.java
// Tuple library using type argument inference.
package thinkinjava.net.mindview.util;

public class Tuple {
  public static <A,B> thinkinjava.net.mindview.util.TwoTuple<A,B> tuple(A a, B b) {
    return new thinkinjava.net.mindview.util.TwoTuple<A,B>(a, b);
  }
  public static <A,B,C> ThreeTuple<A,B,C>
  tuple(A a, B b, C c) {
    return new ThreeTuple<A,B,C>(a, b, c);
  }
  public static <A,B,C,D> FourTuple<A,B,C,D>
  tuple(A a, B b, C c, D d) {
    return new FourTuple<A,B,C,D>(a, b, c, d);
  }
  public static <A,B,C,D,E>
  thinkinjava.net.mindview.util.FiveTuple<A,B,C,D,E> tuple(A a, B b, C c, D d, E e) {
    return new thinkinjava.net.mindview.util.FiveTuple<A,B,C,D,E>(a, b, c, d, e);
  }
} ///:~
