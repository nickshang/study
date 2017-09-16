package java7.coin;

/**
 * Created by Think on 2016/6/22.
 */
public class IntergeParseInt {

    public static int ParseInt(String str){
        // 非正常字符处理

        //
        char c ;
        int l = str.length()-1;
        int t = 0 ;
        for (int i = 0; i < str.length(); i++) {
            c =  str.charAt(i);
            t += (int)(  c - '0') * Math.pow(10,l--);
        }

        return t;
    }
    public static void main(String[] args) {
        System.out.println( ParseInt("100") );
    }
}
