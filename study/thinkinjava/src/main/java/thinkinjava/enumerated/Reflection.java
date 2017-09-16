package thinkinjava.enumerated;//: enumerated/Reflection.java
// Analyzing enums using reflection.

import net.mindview.util.OSExecute;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

enum Explore { HERE, THERE }

/**
 * values()方法-》是由编译器添加static()方法
 */
public class Reflection {
  public static Set<String> analyze(Class<?> enumClass) {

    System.out.println( Reflection.class.getResource("") );

    print("----- Analyzing （分析）" + enumClass + " -----");
    print("Interfaces(接口方法):");
    for(Type t : enumClass.getGenericInterfaces())
      print(t);

    print("Base(基类): " + enumClass.getSuperclass());
    print("Methods(方法): ");
    Set<String> methods = new TreeSet<String>();
    for(Method m : enumClass.getMethods())
      methods.add(m.getName());
    print(methods);

    return methods;
  }
  public static void main(String[] args) {
    Set<String> exploreMethods = analyze(Explore.class);
    Set<String> enumMethods = analyze(Enum.class);

    print("-----------Enum类的是否都包含Explore类方法内-----------------");
    print("Explore.containsAll(Enum)? " +
      exploreMethods.containsAll(enumMethods));
    printnb("Explore.removeAll(Enum): ");
    exploreMethods.removeAll(enumMethods);
    print(exploreMethods);


    // Decompile the code for the enum:
    // 由于擦除效应,反编译无法得到Enum的完整信息，
    // 展示的Explor的父类知识一个原始的Enum，而飞事实上的Enum<Explore>
    print("-----------javap 查看Explore类-----------------");
    OSExecute.command("F:\\workspace\\shang1\\classes\\production\\shang1\\enumerated ", "javap Explore");
  }
} /* Output:
----- Analyzing class Explore -----
Interfaces:
Base: class java.lang.Enum
Methods:
[compareTo, equals, getClass, getDeclaringClass, hashCode, name, notify, notifyAll, ordinal, toString, valueOf, values, wait]
----- Analyzing class java.lang.Enum -----
Interfaces:
java.lang.Comparable<E>
interface java.io.Serializable
Base: class java.lang.Object
Methods:
[compareTo, equals, getClass, getDeclaringClass, hashCode, name, notify, notifyAll, ordinal, toString, valueOf, wait]
Explore.containsAll(Enum)? true
Explore.removeAll(Enum): [values]
Compiled from "Reflection.java"
final class Explore extends java.lang.Enum{
    public static final Explore HERE;
    public static final Explore THERE;
    public static final Explore[] values();
    public static Explore valueOf(java.lang.String);
    static {};
}
*///:~
