package lambda.lx;

import java.io.File;
import java.util.Arrays;

/**
 * 使用lambda表达式进行文件排序
 * Think on 2016/6/30.
 */
public class FileSortTest {

    public static void main(String[] args) {
        File[] fs = new File("D:\\").listFiles();

        // 按照文件的长度进行排序
        Arrays.sort(fs,(first,sencond) -> { return first.getName().length() - sencond.getName().length();});


        // 排序结果输出
        for (int i = 0; i < fs.length; i++) {
            System.out.println( fs[i].getAbsoluteFile().getName() );
        }
    }
}
