package lambda.lx;

/**
 *
 * ��дһ����̬����andThen����������Runnableʵ����Ϊ���������Ż�һ���ֱ�����������ʵ����Runnable����
 * 2016/6/30.
 */
public class AndThen {

    public static void andThen(Runnable a,Runnable b){
        a.run();
        b.run();
    }

    public static void main(String[] args) {
        andThen( ()-> {
            System.out.println("----A-----");
        }, ()-> {
            System.out.println("----B-----");
        });
    }
}
