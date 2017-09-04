package lambda.lx;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * ʹ��java.io.File���list(FilenameFilter)��������дһ������ָ��ĸ���µģ�
 * ������չ���������ļ���
 * ʹ��lambda���ʽ��������FileNameFilter����ʵ�֡�
 * Think on 2016/6/29.
 */
public class FileNameFilterTest {

    public static void main(String[] args) {

        File f = new File("D:\\");

        // �����ļ�-�� �ڲ��ࣨ�����ࣩʵ�ַ�ʽ
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

        // lambda���ʽ1
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


        // lambda���ʽ2 -> �����ƶ�
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
