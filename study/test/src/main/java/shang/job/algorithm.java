package shang.job;

import java.util.Arrays;
import java.util.Random;

/**
 * һ������������10000���������������Ҫ���㷨ʱ�临�Ӷ�ΪO(N)��(��ʾ��Ҳ������һ��FORѭ����
 */
public class algorithm {

    public static void main(String[] args) {

        // ��������
        int size = 1000;
        Random random = new Random(47);
        int[] arr = new int[size];
        for(int i = 0; i < size; i++){
            arr[i]  = random.nextInt(1000);
        }
        System.out.println(Arrays.toString(arr));

        //
        int a1 = 0;
        int a2 = 0;
        int a3 = 0;

        int v ;
        int t ;
        for(int i = 0; i < size; i++){
            v = arr[i];
            if(v > a1){
                a1 = v;
                t = a2;
                a3 = a2;
                a2 = t;
                continue;
            }

            if( v > a2 ){
                t = a2;
                a2 = v;
                a3 = t;
                continue;
            }

            if( v > a3 ) a3 = v;
        }

        System.out.println(a1);

    }
}
