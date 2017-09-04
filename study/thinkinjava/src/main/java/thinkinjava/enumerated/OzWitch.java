package thinkinjava.enumerated;//: enumerated/OzWitch.java
// The witches in the land of Oz.

/**
 * 在enum中添加新的方法
 * 1.在enum中定义自己的方法，在enum实例序列的最后添加一个分号。需要Java要求你必须定义enum实例。
 * 2.在enum的构造器和方法和普通类没有区别，因为出了有少许的限制之外，enum就是一个普通类。
 * enum类中构造器声明为private,但是他的可访问行而言，其实并没有什么变化。
 * 因为只能在enum定义的内部使用构造器创建enum实例。一定euem定义结束，编译器就不允许使用其他构造器创建实例。
 *
 */
public enum OzWitch {

    // Instances must be defined first, before methods:
    WEST("Miss Gulch, aka the Wicked Witch of the West"),
    NORTH("Glinda, the Good Witch of the North"),
    EAST("Wicked Witch of the East, wearer of the Ruby " +
            "Slippers, crushed by Dorothy's house"),
    SOUTH("Good by inference, but missing");
    private String description;

    // Constructor must be package or private access:
      OzWitch(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void main(String[] args) {
        System.out.println(OzWitch.EAST.getDescription());
//        for (OzWitch witch : OzWitch.values())
//            print(witch + ": " + witch.getDescription());
    }
} /* Output:
WEST: Miss Gulch, aka the Wicked Witch of the West
NORTH: Glinda, the Good Witch of the North
EAST: Wicked Witch of the East, wearer of the Ruby Slippers, crushed by Dorothy's house
SOUTH: Good by inference, but missing
*///:~
