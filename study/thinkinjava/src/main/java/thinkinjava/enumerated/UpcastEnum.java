package thinkinjava.enumerated;//: enumerated/UpcastEnum.java
// No values() method if you upcast an enum

enum Search { HITHER, YON }

/**
 * enum类通过向上转型，将导致values()无法访问。
 * 通过class.getEnumConstants()可以访问到类的具体枚举类
 */
public class UpcastEnum {
  public static void main(String[] args) {
    Search[] vals = Search.values();
    Enum e = Search.HITHER; // Upcast
    // e.values(); // No values() in Enum

    // e.getClass().getEnumConstants())
    // 以声明顺序返回一个数组，该数组包含构成此 Class 对象所表示的枚举类的值，或者在此 Class 对象不表示枚举类型时返回 null
    for(Enum en : e.getClass().getEnumConstants())
      System.out.println(en);
  }
} /* Output:
HITHER
YON
*///:~
