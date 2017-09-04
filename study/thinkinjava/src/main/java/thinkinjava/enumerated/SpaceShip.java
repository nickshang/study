package thinkinjava.enumerated;

/**
 * 覆盖enum的方法
 */
//: enumerated/SpaceShip.java
public enum SpaceShip {
    SCOUT, CARGO, TRANSPORT, CRUISER, BATTLESHIP, MOTHERSHIP;

    public String toString() {
        String id = name();         // 获取enum实例名
        String lower = id.substring(1).toLowerCase();
        return id.charAt(0) + lower +" class :" + getClass();
    }

    public static void main(String[] args) {
        for (SpaceShip s : values()) {
            System.out.println(s);
        }
    }
} /* Output:
Scout
Cargo
Transport
Cruiser
Battleship
Mothership
*///:~
