package thinkinjava.chapter21_concurrency.c7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：信号量同步学习
 * @author NICK
 *
 */
public class SemaphoreDemo {

    final static int SIZE = 25;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        final Pool<Fat> pool =  new Pool<Fat>(Fat.class,SIZE);

        ExecutorService exec = Executors.newCachedThreadPool();

        for(int i = 0; i < SIZE; i++){
            exec.execute(new CheckoutTask(pool));
        }
        System.out.println("All CheckTasks created");


        //检出所有对象
        List<Fat> list = new ArrayList<Fat>();
        for (int i = 0; i < SIZE; i++){
            Fat f = pool.checkOut();
            System.out.println(i + ": main() thread checked out");
            f.operation();
            list.add(f);
        }


        //因为pool没有可以牵出的对象,将阻塞等待
        Future<?> blocked = exec.submit(new Runnable(){
            @Override
            public void run() {
                try {
                    pool.checkOut();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);
        System.out.println("Checking in objects in " + list );


        //检出对象（因为对象池中没有对象，将忽然）
        for(Fat f : list)
            pool.checkIn(f);

        for(Fat f : list)
            pool.checkIn(f);

        exec.shutdown();
    }

}

/**
 * 功能描述： 对象检出任务
 * @author NICK
 *
 * @param <T>
 */
class CheckoutTask<T> implements Runnable{

    //ID统计
    private static int counter = 0 ;

    //id
    private final int id = counter++;

    //对象池
    private Pool<T> pool;

    //初始化
    public CheckoutTask(Pool<T> pool){
        this.pool = pool;
    }

    @Override
    public void run() {
        try{
            T  item = pool.checkOut();
            System.out.println( this + " checked out " + item );
            TimeUnit.SECONDS.sleep(1);

            System.out.println(this + "checking in " + item );
            pool.checkIn(item);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "CheckoutTask " + id +" ";
    }


}