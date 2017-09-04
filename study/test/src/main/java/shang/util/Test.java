package shang.util;

import java.util.*;

public class Test {

	
	public static void main(String[] args) {
//		com.shang.util.DateUtil dateUtil = new com.shang.util.DateUtil();
//		
//		Date d = new Date();
//		String s = dateUtil.DateToString(d, "yyyy-MM-dd HH:mm:ss");
//		System.out.println ("##########:"+s ) ;
//		System.out.println ( dateUtil.getBetweenDateSeconds("2010-01-01 00:00:00",s ));
		
		
//		 Map<String,Object>   map = new HashMap<String, Object>();
//			  map.put("1", "ZA1");
//			  map.put("2", "ZA2");
//			  map.put("3", "ZA3");
//			  Iterator<Entry<String, Object>>  it =  map.entrySet().iterator();
//			  while( it.hasNext()){
//				  String name =   it.next().getValue().toString();
//				  System.out.println(  name );
//		}
		
		Calendar  ca1 = Calendar.getInstance();
		ca1.setTimeInMillis(1318607880000l);
		
		Calendar  ca2 = Calendar.getInstance();
		ca2.setTimeInMillis(1318607940000l);
		
		System.out.println( ca1.getTime().toLocaleString() );
		System.out.println( ca2.getTime().toLocaleString() );
		
		String data1 = "2015-10-20 00:00:00";
		String data2 = "2015-11-22 00:00:00";
		
		Date d1 = DateUtil.StringToDate(data1, "yyyy-MM-dd HH:mm:ss");
		Date d2 = DateUtil.StringToDate(data2, "yyyy-MM-dd HH:mm:ss");
		
		long t1 = d1.getTime();
		long t2 = d2.getTime();
		
		long  size =  ( d2.getTime() - d1.getTime()  ) / (15*60*1000);
		
		long[][] res = new long[(int)size][2];
		
		Random r = new  Random();
//		int i = 0 ;
//		for( long  t = t1; t < t2; t += 15*60*1000 ){
//			res[i][0] =  t;
//			res[i][1] = r.nextLong() ;
//			i++;
//		}

		Map<Long,Float> map = new LinkedHashMap<Long, Float>();
		
		StringBuilder str = new StringBuilder();
		
		int i =0 ;
		for( long  t = t1; t < t2; t += 15*60*1000 ){
			
				map.put(t, r.nextFloat());
				str.append("[" );
				str.append( t );
				str.append("," );
				str.append( r.nextFloat() );
				str.append("]," );
			
			i++;
		}
		StringBuilder str2 = new StringBuilder();
		str2.append("[");
		str2.append(str.toString().substring(0, str.toString().length()-1));
		str2.append("]");

		System.out.println( str2  );
	}
	
	
}
