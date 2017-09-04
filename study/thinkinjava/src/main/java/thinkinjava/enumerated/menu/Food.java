//: enumerated/menu/Food.java
// Subcategorization of enums within interfaces.
package thinkinjava.enumerated.menu;

/**
 * 无法从enum继承子类。使得无法扩展enum中的元素，有时需要使用子类将一个enum中的元素进行分组.
 *
 * 在一个接口的内部，创建实现该接口的枚举，以此将元素进行分组，可以将枚举元素分类组织的目的。
 *
 */
public interface Food {
  enum Appetizer implements Food {
    SALAD, SOUP, SPRING_ROLLS;
  }
  enum MainCourse implements Food {
    LASAGNE, BURRITO, PAD_THAI,
    LENTILS, HUMMOUS, VINDALOO;
  }
  enum Dessert implements Food {
    TIRAMISU, GELATO, BLACK_FOREST_CAKE,
    FRUIT, CREME_CARAMEL;
  }
  enum Coffee implements Food {
    BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
    LATTE, CAPPUCCINO, TEA, HERB_TEA;
  }
} ///:~
