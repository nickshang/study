package thinkinjava.chapter21_concurrency.c5;

//��ȡ�����������   
public class ReaderResult extends Thread {  
    Calculator c;  
    public ReaderResult(Calculator c) {  
        this.c = c;  
    }  
    public void run() {  
        synchronized (c) {  
            try {  
                System.out.println(Thread.currentThread() + "�ȴ�������������");  
                c.wait();  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            System.out.println(Thread.currentThread() + "������Ϊ��" + c.total);  
        }  
    }  
    public static void main(String[] args) {  
        Calculator calculator = new Calculator();  
        // ����10���̣߳��ֱ��ȡ������   
        for(int i=0;i<5;i++){  
        new ReaderResult(calculator).start();  
        }  
        // ���������߳�   
        calculator.start();  
    }  
}  

//�����߳�   
class Calculator extends Thread {  
    int total;  
    public void run() {  
        synchronized (this) {  
            for (int i = 0; i < 10; i++) {  
                total += i;  
            }  
        }  
        // ֪ͨ�����ڴ˶����ϵȴ����߳�   
        notifyAll();  
    }  
}  