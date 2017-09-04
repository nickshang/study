//: generics/CRGWithBasicHolder.java
package thinkinjava.chapter15_generator.c12;

class Subtype extends BasicHolder<Subtype> {}

// 基类用导出类替代其参数
public class CRGWithBasicHolder {
  public static void main(String[] args) {
    Subtype st1 = new Subtype(), st2 = new Subtype();
    st1.set(st2);
    Subtype st3 = st1.get();
    st1.f();
  }
} /* Output:
Subtype
*///:~
