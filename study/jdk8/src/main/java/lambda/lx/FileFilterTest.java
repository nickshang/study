package lambda.lx;

import java.io.File;

/**
 * 使用java.io.File类的list(FileFilter)和isDirectoy方法
 * 具有指定扩展名的所有文件。
 * Think on 2016/6/30.
 */
public class FileFilterTest {
    public static void main(String[] args) {

        // 使用lambda表达式进行过滤
        File file = new File("D:\\");
        String   postfix =  "txt";
        File[]  fs =  file.listFiles( (File f) -> { if(f.getName().contains(postfix)) {return true;} else return false; } );
        
        // 输出结果
        for (int i = 0; i< fs.length ; i++) {
            System.out.println("f:" + fs[i].getName() );
        }

    }
}
