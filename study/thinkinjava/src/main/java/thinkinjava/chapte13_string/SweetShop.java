package thinkinjava.chapte13_string;

import static thinkinjava.net.mindview.util.Print.print;

class Candy {
    static { print("Loading Candy"); }
}

class Gum {
    static { print("Loading Gum"); }
}

class Cookie {
    static { print("Loading Cookie"); }
}

/**
 * 演示Class对象的使用及初始化
 */
public class SweetShop {
    public static void main(String[] args) {
        print("inside main");
        new Candy();                                //   只有第一次系统对象时才会初始化对象静态域
        new Candy();                                //   第二次使用不会初始化静态域
        print("After creating Candy");
        try {
            Class c1 =  Class.forName("Gum");       //   获取Class对象

            Class c2 = Candy.class;                 //    获取Class对象

        } catch(ClassNotFoundException e) {
            print("Couldn't find Gum");
        }
        print("After Class.forName(\"Gum\")");
        new Cookie();
        print("After creating Cookie");
    }
}