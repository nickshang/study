package com.shang.pool;

public class PairTest {

	String t1  ;
	String t2  ;
	
	public static void main(String[] args) {
		int[] i = {10,11,22,33,44,11,33,55,33,11,55,11,1,2,3,4,5,6,7,8};
		
		PairTest test = new PairTest();
		test.bestOjbectAllOcationExample(i);
		
//		PairTest test = new PairTest();
//		test.Test();
		
	}
	
	
	public void Test(){
		String s = new String("xxx");
		
		String s1 = s;
		
		s = null;
		
		System.out.println("s1:" +s1);
		System.out.println("s:" + s );
	}
	
	public void bestOjbectAllOcationExample(int[] pairs){
		
		if(pairs.length % 2 !=0 ) 
			throw new IllegalArgumentException("错误的数组数量!");
		
		for(int i = 0; i < pairs.length; i += 2 ){
			Pair pair = Pair.obtain();
			pair.firstValue = pairs[i];
			pair.secondValue = pairs[++i];
			sendPair( pair );
			pair.recyle();
			
			System.out.println( pair.hashCode() );
			                        
		}
	}
	
	public void sendPair(Pair p ){
		System.out.println( "p.firstValue:" + p.firstValue);
		System.out.println( "p.secondValue:" + p.secondValue);
	}
	
}
