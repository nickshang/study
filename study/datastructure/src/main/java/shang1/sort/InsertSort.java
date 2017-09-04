package shang1.sort;

/**
 * ������������������
 * ����˼���ǣ��Ѵ�����ļ�¼����ؼ���ֵ�Ĵ�С������뵽һ���Ѿ��ź�������������У�ֱ�����еļ�¼������Ϊֹ���õ�һ���µ��������С�
 * @author Think
 *
 */
public class InsertSort {
	
	public static void insertSort(int[] arr){
		int temp =  0;
		for(int i = 1; i < arr.length; i++ ){
			
			//
			int j = i ;
			temp = arr[i]; 
			while( j > 0 && ( arr[j-1] > temp ) ) {
				 arr[j] = arr[j-1];
				 j--;
			}
			arr[j] = temp;
		}
	}
	
	public static void main(String[] args) {
		int[] a = {3,5,1,12,19};
		insertSort(a);
		for(int i:a){
			System.out.println(i);
		}
		
		
	}
}
