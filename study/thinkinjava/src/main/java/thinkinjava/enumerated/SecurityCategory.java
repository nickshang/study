package thinkinjava.enumerated;//: enumerated/SecurityCategory.java
// More succinct subcategorization of enums.

import net.mindview.util.Enums;

enum SecurityCategory {
    STOCK(Security.Stock.class),
    BOND(Security.Bond.class);

    Security[] values;

    // 将Security中的enum作为器构造器参数使用。
    SecurityCategory(Class<? extends Security> kind) {
        values = kind.getEnumConstants();
    }

    // Security接口的作用是将其所包含的enum组合成一个公共类型。
    interface Security {
        enum Stock implements Security {SHORT, LONG, MARGIN}

        enum Bond implements Security {MUNICIPAL, JUNK}
    }

    public Security randomSelection() {
        return Enums.random(values);
    }

    public static void main(String[] args) {


        for (int i = 0; i < 10; i++) {
            SecurityCategory category =
                    Enums.random(SecurityCategory.class);
            System.out.println(category + ": " +
                    category.randomSelection());
        }
    }
} /* Output:
BOND: MUNICIPAL
BOND: MUNICIPAL
STOCK: MARGIN
STOCK: MARGIN
BOND: JUNK
STOCK: SHORT
STOCK: LONG
STOCK: LONG
BOND: MUNICIPAL
BOND: JUNK
*///:~
