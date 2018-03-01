package java7.coin;

import java.util.Objects;

/**
 * switch语句中String
 */
public class SwitchTest {

    public static void printDay(String dayOfWeek){
        switch (dayOfWeek){
            case "Sunday" :
                System.out.println("Sunday"); break;
            case "Monday" :
                System.out.println("Monday"); break;
            case "Tuesday" :
                System.out.println("Tuesday"); break;
            case "Wednesday" :
                System.out.println("Wednesday"); break;
            case "Friday" :
                System.out.println("Friday"); break;
            case "Saturday" :
                System.out.println("Saturday"); break;
            default :
                System.out.println("Error!");
        }
    }

    public static void main(String[] args) {
        printDay("Sunday");
        printDay("Other");
        printDay(null);
    }
}
