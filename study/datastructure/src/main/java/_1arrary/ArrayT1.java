package _1arrary;


/**
 * 
 * @类描述：学习数据结构与算法
 * 1.插入数据
 * 2.删除数据
 * 3.修改数据
 * 4.查找数据
 * 5.显示数据 
 * @创建人：NICK
 * @创建时间：2016年4月17日 下午2:13:32
 * @version v1.0
 * @mail sjshang@tsingsoft.com
 * @Copyright 北京清软创新科技股份有限公司
 */
public class ArrayT1 {
	 
	public static void main(String[] args) {
		
		// 创建数组
		final int SIZE = 10;										// 数组初始化大小
		
		byte[] 	a1 	= new byte[SIZE];
		char[] 	a2 	= new char[SIZE];
		short[] a3 	= new short[SIZE];
		int[] 	a4 	= new int[SIZE];
		long[] 	a5 	= new long[SIZE];
		float[] a6 	= new float[SIZE];
		double[] a7 = new double[SIZE];
		
		// 查看初始化对象 
		System.out.println(String.format("数组类型：%s,初始化值:%s", "a1", a1[0] ));
		System.out.println(String.format("数组类型：%s,初始化值:%s", "a2", a2[0] ));
		System.out.println(String.format("数组类型：%s,初始化值:%s", "a3", a3[0] ));
		System.out.println(String.format("数组类型：%s,初始化值:%s", "a4", a4[0] ));
		System.out.println(String.format("数组类型：%s,初始化值:%s", "a5", a5[0] ));
		System.out.println(String.format("数组类型：%s,初始化值:%s", "a6", a6[0] ));
		System.out.println(String.format("数组类型：%s,初始化值:%s", "a7", a7[0] ));
		
		// 数组的定义方式
		byte[] 	a1_ = new byte[SIZE];		// 直接定义 或者开始定义为空 后初始化
		int[]	a2_ = {1,2,3};				// 直接初始化
		
		// 插入数据 
		for(byte b = 0; b < SIZE; b++ ){
			a1_[b] = b;
		}
		
		// 输出数据
		for(byte b = 0; b < SIZE; b++ ){
			System.out.println(String.format("数组对象%s，位置：%d，值:%s", "a1_", b, a1_[b] ));
		}
		
		
		for(byte b = 0; b < a2_.length; b++ ){
			System.out.println(String.format("数组对象%s，位置：%d，，值:%s", "a2_", b, a2_[b] ));
		}
		
		// 删除数据
		// 删除对象a1_五个元素
		for(byte b = 0; b < a1.length; b++){
			a1[b] = b;
		}
		
		byte del = 5;  									// 确定删除的位置
		for(byte b =  del--; b < a1.length - 1; b++ ){
			a1[b] = a1[b+1]; 							// 从要删除的数据移位
		}
		
		for(byte b =0; b < a1.length; b++ ){
			System.out.println( String.format("位置:%s,数据:%d", b, a1[b]) );
		}
		
		// 查找数据
		int searchValue = 3;
		int searchIndex = -1;
		for( int i = 0;  i < a1.length; i ++){
			if( a1[i] == searchValue ){
				searchIndex = i; 
			}
		}
		System.out.println( String.format( "需要查找到的值:%s,需要查找的位置:%s", searchValue, searchIndex));
		
		// 修改数据
		int oldValue = 3;
		int newValue = 5;
		for( int i = 0;  i < a1.length; i ++){
			if( a1[i] == oldValue ){
				a1[i]  = (byte)newValue ; 
			}
		}
		
		
		// 显示数据
		System.out.print( "a1数组数据: " );
		for( int i = 0;  i < a1.length; i ++){
			System.out.print( a1[i]  + " ");
		}
		
		
	}
	
	
	/**
	 * 
	 * @描述:a+b
	 * @param a  变量1
	 * @param b  变量2
	 * @since
	 * @throws
	 */
	public static void S(int a , int b){
		
	}

}
