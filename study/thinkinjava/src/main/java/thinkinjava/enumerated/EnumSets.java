//: enumerated/EnumSets.java
// Operations on EnumSets
package thinkinjava.enumerated;

import java.util.EnumSet;

import static thinkinjava.enumerated.AlarmPoints.*;
import static net.mindview.util.Print.print;
import static thinkinjava.enumerated.AlarmPoints.BATHROOM;

/**
 *  使用EnumSet使用
 */
public class EnumSets {
    public static void main(String[] args) {
        EnumSet<AlarmPoints> points =
                EnumSet.noneOf(AlarmPoints.class);              // 创建一个空的EnumSet对象
        points.add(BATHROOM);
        print(points);
        points.addAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));     // of 创建一个最初包含指定元素的枚举 set。
        print(points);
        points = EnumSet.allOf(AlarmPoints.class);              // 创建一个Enum对象，包含参数的实例
        points.removeAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
        print(points);
        points.removeAll(EnumSet.range(OFFICE1, OFFICE4));
        print(points);
        points = EnumSet.complementOf(points);
        print(points);

    }
} /* Output:
[BATHROOM]
[STAIR1, STAIR2, BATHROOM, KITCHEN]
[LOBBY, OFFICE1, OFFICE2, OFFICE3, OFFICE4, BATHROOM, UTILITY]
[LOBBY, BATHROOM, UTILITY]
[STAIR1, STAIR2, OFFICE1, OFFICE2, OFFICE3, OFFICE4, KITCHEN]
*///:~
