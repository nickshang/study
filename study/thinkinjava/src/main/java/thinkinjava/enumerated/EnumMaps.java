//: enumerated/EnumMaps.java
// Basics of EnumMaps.
package thinkinjava.enumerated;

import java.util.*;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;
import static thinkinjava.enumerated.AlarmPoints.*;

/**
 * 命令设计模式的使用
 */
interface Command {
    void action();
}

/**
 * EnumMap使用
 *
 */
public class EnumMaps {
    public static void main(String[] args) {
        EnumMap<AlarmPoints, Command> em =
                new EnumMap<AlarmPoints, Command>(AlarmPoints.class);
        em.put(KITCHEN, new Command() {
            public void action() {
                print("Kitchen fire!");
            }
        });
        em.put(BATHROOM, new Command() {
            public void action() {
                print("Bathroom alert!");
            }
        });
        for (Map.Entry<AlarmPoints, Command> e : em.entrySet()) {
            printnb(e.getKey() + ": ");
            e.getValue().action();
        }
        try { // If there's no value for a particular key:
            em.get(UTILITY).action();
        } catch (Exception e) {
            print(e);
        }
    }
} /* Output:
BATHROOM: Bathroom alert!
KITCHEN: Kitchen fire!
java.lang.NullPointerException
*///:~
