package java7.concurrency;

/**
 * 查看对象锁和类锁的区别
 *
 * 具体测试流程分为两个步骤。
 * 第一个步骤是直接运行如下代码，测试结果是用来测试对象锁的锁效果；
 * 第二个步骤是把for循环中的前两行代码注释掉，把其余三行有注释的代码删去注释，
 * 还有，类Obj最后一行注释代码删去注释，用来测试类锁的效果。
 *
 *  执行代码之后分析如下，这里也会解释为何执行代码导致阻塞：
 *      1：它会首先执行没加锁的方法，无论是一个对象多个线程还是每个线程中一个对象，对无锁方法都是没有影响的。对于对象锁和类锁来说，只会对加了锁的方法产生不同的影响。
 *      2：当多个对象对同一个加了对象锁的方法进行调用则会被阻塞，而不同对象对不同方法访问则不会被阻塞，就算加了对象锁。当同一个对象在线程1中访问一个方法，在线程2中再去访问另外一个加锁方法，则同样也会被阻塞。针对上面代码就是，在线程1中对象runTest访问outMethod，而在线程2中访问outMethod2则会被阻塞。
 *      3：对于类锁，则会把整个类锁住，也就说只能有一个对象拥有当前类的锁。当一个对象拥有了类锁之后，另外一个对象还想竞争锁的话则会被阻塞。两个对象A，B，如果A正在访问一个被类锁修饰的方法function，那么B则不能访问。因为类锁只能在同一时刻被一个对象拥有。相对于对象锁，则是不同。还是A，B两个对象，如果A正在访问对象锁修饰的function，那么这个时候B也可以同时访问。
 *
 *  对于类锁的输出进行分析，它的输出我表示成三个部分：
 *     在第一部分输出几乎同时输出，是因为每个线程都是一个新的对象，不同对象访问对象锁是不会被阻塞的，所以几乎是按照程序的先后输出；
 *     第二部分输出就是两个方法中的sleep时间消耗，没有什么问题；
 *     第三部分就是计算i++，然后输出结果，这部分输出是比较慢的。因为plus方法是类锁，在同一时刻只能是一个对象拥有该锁，所以多个线程必须顺序执行结果，所以最后i的输出也是10.
 *     其中对于对象锁，当一个对象拥有锁之后，访问一个加了对象锁的方法，而该方法中又调用了该类中其他加了对象锁的方法，那么这个时候是不会阻塞住的。这是java通过可重入锁机制实现的。可重入锁指的是当一个对象拥有对象锁之后，可以重复获取该锁。因为synchronized块是可重入的，所以当你访问一个对象锁的方法的时候，在该方法中继续访问其他对象锁方法是不会被阻塞的。
 *
 */
public class MultiThread {
    public static void main(String[] args) {
        Runtest runtest = new Runtest();
        for (int i = 0; i < 10; i++) {
//            Thread thread = new Obj(runtest, i);// 1同一个RunTest1对象但每次有个新的线程
//            thread.start();

			 Runtest rruntest = new Runtest(); //2 循环每次都声明一个新的对象
			 Thread thread = new Obj(rruntest, i);
			 thread.start();
        }
    }
}

class Obj extends Thread {
    Runtest r;
    int i = 0;

    public Obj(Runtest r, int i) {
        this.r = r;
        this.i = i;
    }

    public void run() {
        r.noSyn(this.getId());
        //用以测试同一个对象在不同线程中访问不同方法
        if(i % 2 == 0){
            r.outMethod2();//对象锁方法2
        }else{
            r.outMethod();//对象锁方法1
        }
        Runtest.plus(); //类锁方法
    }
}

class Runtest {
    static int i = 0;
    public void noSyn(long threadId) {
        System.out.println("nosyn: class obj->" + this + ", threadId->" + threadId);
    }

    synchronized public void outMethod() {
        System.out.println("in outMethod begin");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
        }
        System.out.println("in outMethod end");
    }

    synchronized public void outMethod2() {
        System.out.println("in outMethod2 begin");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
        }
        System.out.println("in outMethod2 end");
    }

    public static void plus() {
        synchronized (Runtest.class) {
            System.out.println("start: " + i);
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
            }
            i++;
            System.out.println("i is " + i);
        }
    }
}
