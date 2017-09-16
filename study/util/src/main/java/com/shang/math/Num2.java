package com.shang.math;

/**
 * 功能描述:递归
 * @author Administrator
 *
 */
public class Num2 {

	int  z1 = 1;  //第一个数
	int  z2 = 1;  //第二个数
	int  z = 0;  //结果
	int i = 3;
	/**
	 * 功能描述:递归
	 * @param i
	 * @return
	 */
	public   int f1(int count)
	{
		//第1列
		if( i == 1  ||  i == 2)
		{
			return 1;
		}
		
		//其他
		if(  i <= count)
		{
			if(i%2 != 0)
			{
				z1 = z1 + z2;
				z = z1;
			}else 	if(i%2 == 0)
			{
				z2 = z1 + z2;
				z = z2;
			}	
			i++;
			f1(count);
		}
		
		return z;
	}
	
	
	public static void main(String[] args) {
		
		Num2  num = new Num2();
		System.out.println( num.f1(8) ) ;
	}
}
