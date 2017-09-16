package com.shang.math;

/**
 * 功能描述:循环
 * @author Administrator
 *
 */
public class Num {

	
	/**
	 * 功能描述:循环
	 * 原理:
	    z1 z2
		1   1  = 2
		1   2  = 3
		2   3  = 5
	 * @param count 传入参数
	 * @return
	 */
	public  int f2(int count)
	{
		int  z1 = 1;  //第一个数
		int  z2 = 1;  //第二个数
		int  z = 0;  //结果
		
		//第1列
		if( count == 1 || count == 2)
		{
			return  1;
		}
		
		//其他列
		for(  int i = 3; i<=count; i++ )
		{
			if(i%2 != 0)
			{
				z1 = z1+z2;
				z = z1;
			}else if(i%2 == 0)
			{
				z2 = z1+z2;
				z = z2;
			}
		}
		
		return z;
	}
	
	
	public static void main(String[] args) {
		Num  num = new Num();
		System.out.println( num.f2(8) ) ;
	}
}
