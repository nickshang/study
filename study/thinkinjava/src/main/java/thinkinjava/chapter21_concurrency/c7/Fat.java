

package thinkinjava.chapter21_concurrency.c7;

/**
 * Created by Think on 2017/9/6.
 */
/**
 * 功能描述：
 * @author NICK
 *
 */
public class Fat {

    private volatile double d; //Prevent optimization

    private static int counter = 0;

    private final int id = counter++;

    public Fat(){
        for (int i = 1; i < 10000; i++ ){
            d += (Math.PI + Math.E)/(double)i;
        }
    }

    public void operation() {System.out.println(this);}

    public String toString() { return "Fat id:" + id;}
}
