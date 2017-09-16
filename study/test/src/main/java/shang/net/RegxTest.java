package shang.net;

//import com.shang.io.ReadFile;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegxTest {
	
	public static void main(String[] args) {
//
//		ReadFile readFile = new ReadFile();
//		String str = readFile.readFile( new File("c:\\avc.txt"),  null );
//
//		String regx2 = "#\\s*(.[^\\s*]*?)\\s*AVC(鄂州二期)?子站可控(\\(青热电\\))?\\s*(.[^\\s*]?)\\s*([0|1])";
//		Matcher matcher2 = regx(regx2,  str);
//		int i = 0 ;
//		while( matcher2.find()){
//			i++;
//			System.out.println(  "-----------------------------------------"  );
//			System.out.println( i+ "-AVC:"+ matcher2.group(1) );
//			System.out.println( i+ "-KKE:"+ matcher2.group(4) );
//			System.out.println( i+ "-AVC:"+ matcher2.group(5) );
//
//		}
	
	}
 
	
	
	/**
	 * 筹建适配器
	 * @param regex
	 * @param sDetail
	 * @return
	 */
	public  static Matcher regx(String regex, String sDetail) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sDetail);
		return m;
	}
	
}
