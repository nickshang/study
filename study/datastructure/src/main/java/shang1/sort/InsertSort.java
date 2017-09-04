package shang1.sort;

/**
 * 功能描述：插入排序
 * 基本思想是：把待排序的纪录按其关键码值的大小逐个插入到一个已经排好序的有序序列中，直到所有的纪录插入完为止，得到一个新的有序序列。
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
