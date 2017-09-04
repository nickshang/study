package lambda.lx;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * 使用java.io.File类的list(FilenameFilter)方法，编写一个返回指定母的下的，
 * 具有扩展名的所有文件。
 * 使用lambda表达式（而不是FileNameFilter）来实现。
 * Think on 2016/6/29.
 */
public class FileNameFilterTest {

    public static void main(String[] args) {

        File f = new File("D:\\");

        // 过滤文件-》 内部类（匿名类）实现方式
        FilenameFilter filter = new FilenameFilter(){
            @Override
            public boolean accept(File dir, String name) {
                if( name.contains("txt") ){
                    return true;
                }
                return false;
            }
        };
        String[] names = f.list( filter );
        for(String n : names ){
            System.out.println("file:" + n );
        }

        // lambda表达式1
        FilenameFilter ff = ( dir,  name) -> {
            if( name.contains("txt") ){
                return true;
            }
            return false;
        };

        names = f.list( ff );
        for(String n : names ){
            System.out.println("file:" + n );
        }


        // lambda表达式2 -> 类型推断
        names = f.list(
                (dir,name) -> {
                    if( name.contains("txt") )  return true;
                    return false;
                } );


        for(String n : names ){
            System.out.println("file:" + n );
        }
    }
}
