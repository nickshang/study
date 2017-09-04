//: enumerated/RoShamBo6.java
// Enums using "tables" instead of multiple dispatch.
package thinkinjava.enumerated;

import static thinkinjava.enumerated.Outcome.*;

/**
 *
 *  因为enum实例都一个固定的值（基于其声明的次序），并且可以通过ordinal()方法取得该值。
 *  可以使用二维数组，将竞争者映射到竞争结果中。
 */
enum RoShamBo6 implements Competitor<RoShamBo6> {
    PAPER, SCISSORS, ROCK;
    private static Outcome[][] table = {
            {DRAW, LOSE, WIN}, // PAPER
            {WIN, DRAW, LOSE}, // SCISSORS
            {LOSE, WIN, DRAW}, // ROCK
    };

    public Outcome compete(RoShamBo6 other) {
        return table[this.ordinal()][other.ordinal()];
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo6.class, 20);
    }
} ///:~
