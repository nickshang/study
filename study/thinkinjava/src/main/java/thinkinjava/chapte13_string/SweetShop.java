package thinkinjava.chapte13_string;

import static net.mindview.util.Print.print;

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
 * ��ʾClass�����ʹ�ü���ʼ��
 */
public class SweetShop {
    public static void main(String[] args) {
        print("inside main");
        new Candy();                                //   ֻ�е�һ��ϵͳ����ʱ�Ż��ʼ������̬��
        new Candy();                                //   �ڶ���ʹ�ò����ʼ����̬��
        print("After creating Candy");
        try {
            Class c1 =  Class.forName("Gum");       //   ��ȡClass����

            Class c2 = Candy.class;                 //    ��ȡClass����

        } catch(ClassNotFoundException e) {
            print("Couldn't find Gum");
        }
        print("After Class.forName(\"Gum\")");
        new Cookie();
        print("After creating Cookie");
    }
}