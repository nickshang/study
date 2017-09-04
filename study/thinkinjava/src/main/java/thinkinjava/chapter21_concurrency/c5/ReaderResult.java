package thinkinjava.chapter21_concurrency.c5;

//获取计算结果并输出   
public class ReaderResult extends Thread {  
    Calculator c;  
    public ReaderResult(Calculator c) {  
        this.c = c;  
    }  
    public void run() {  
        synchronized (c) {  
            try {  
                System.out.println(Thread.currentThread() + "等待计算结果。。。");  
                c.wait();  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            System.out.println(Thread.currentThread() + "计算结果为：" + c.total);  
        }  
    }  
    public static void main(String[] args) {  
        Calculator calculator = new Calculator();  
        // 启动10个线程，分别获取计算结果   
        for(int i=0;i<5;i++){  
        new ReaderResult(calculator).start();  
        }  
        // 启动计算线程   
        calculator.start();  
    }  
}  

//计算线程   
class Calculator extends Thread {  
    int total;  
    public void run() {  
        synchronized (this) {  
            for (int i = 0; i < 10; i++) {  
                total += i;  
            }  
        }  
        // 通知所有在此对象上等待的线程   
        notifyAll();  
    }  
}  