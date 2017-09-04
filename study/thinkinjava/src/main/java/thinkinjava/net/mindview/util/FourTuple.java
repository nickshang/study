//: net/mindview/util/FourTuple.java
package thinkinjava.net.mindview.util;

public class FourTuple<A,B,C,D> extends  thinkinjava.net.mindview.util.ThreeTuple<A,B,C> {
  public final D fourth;
  public FourTuple(A a, B b, C c, D d) {
    super(a, b, c);
    fourth = d;
  }
  public String toString() {
    return "(" + first + ", " + second + ", " +
      third + ", " + fourth + ")";
  }
} ///:~
