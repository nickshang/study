package lambda.lx;

import java.io.File;

/**
 * ʹ��java.io.File���list(FileFilter)��isDirectoy����
 * ����ָ����չ���������ļ���
 * Think on 2016/6/30.
 */
public class FileFilterTest {
    public static void main(String[] args) {

        // ʹ��lambda���ʽ���й���
        File file = new File("D:\\");
        String   postfix =  "txt";
        File[]  fs =  file.listFiles( (File f) -> { if(f.getName().contains(postfix)) {return true;} else return false; } );
        
        // ������
        for (int i = 0; i< fs.length ; i++) {
            System.out.println("f:" + fs[i].getName() );
        }

    }
}
