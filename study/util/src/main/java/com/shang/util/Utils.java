package com.shang.util;

import java.util.ArrayList;
import java.util.List;


public class Utils {

	/**
	 * 功能描述：获取概率组数
	 * @param sdx_list
	 * @param z
	 * @return
	 */
	public List< List<String >  >    getZhS(List<String>  sdx_list  ,int z)
	{
		List< List<String >  > result = new ArrayList< List<String > >(); 
		
		for( int index=0; index<=sdx_list.size()-z; index++)  //分组
		{
			 List<String >  temp = new ArrayList< String >();  //
			//分一组
			 if( z==1 )  
			 {
				  temp.add( sdx_list.get( index) );
				  result.add( temp ) ;
			 }else
			 {
				 //大于一组
				 for( int indexs = index +1 ;  indexs <sdx_list.size() ;  indexs++ )
				 {
					 temp = new ArrayList< String >();
					 temp.add( sdx_list.get( index) );
					 temp.add( sdx_list.get( indexs ) );
				
					 for( int _indexs  =indexs+1; _indexs < sdx_list.size() ;  _indexs ++ )
					 {
						 if(temp.size()<z)
						 {
							 temp.add( sdx_list.get(  _indexs ) );	 
						 }else
						 {
							break;
						 }
					 }
					 
					 if(temp.size()==z)
					 {
						 result.add( temp ) ;
					 }
				 }
			 }
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		List<String> list =  new ArrayList<String>();
		list.add("1");
		
		Utils dao = new Utils();

		System.out.println( dao.getZhS(list, 2 ) ) ;
	}
}
