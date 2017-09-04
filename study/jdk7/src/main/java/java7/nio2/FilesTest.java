package java7.nio2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created by
 * Think on 2016/6/23.
 */
public class FilesTest {

    public static void main(String[] args) throws Exception {

        // 创建和删除文件
        // 调用Files类的辅助方法，就可以很容易地创建和删除文件。
        Path target = Paths.get("d:\\mystuff.txt");
        if( Files.exists(target) )    Files.delete(target);     // 判断是否存在 —> 删除
        Path file = Files.createFile(target);                   // 创建
        Files.delete(target);                                   // 删除
        Files.createFile(target);                               // 创建


        // POSIX系统上操作属主、属主组内用户和所有用户设置读、写许
//        Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-rw-rw-");
//        FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
//        Files.createFile(target,attr);

        // 文件的的复制和移动
        Path _source = Paths.get("d:\\mystuff.txt");
        Path _target = Paths.get("d:\\mystuff2.txt");
        Files.copy(_source,_target,REPLACE_EXISTING);

        //  COPY_ATTRIBUTES 复制文件属性
        //  ATOMIC_MOVE 确保两边都成果，否知回滚

        // 读取文件的属性
        try{
            Path zip = Paths.get("D:\\file");
            System.out.println(Files.getLastModifiedTime(zip));
            System.out.println(Files.size(zip));
            System.out.println(Files.isSymbolicLink(zip));
            System.out.println(Files.isDirectory(zip));
            System.out.println(Files.readAttributes(zip,"*"));
        }catch(IOException ex){
            ex.printStackTrace();
        }

       /* // 对文件属性的操作
        try{
            Path profile = Paths.get("/user/Admin/.profile");

            // 获取属性视图
            PosixFileAttributes attrs = Files.readAttributes(profile, PosixFileAttributes.class);

            // 读取访问许可
            Set<PosixFilePermission> posixFilePermissions  =  attrs.permissions();

            // 清除访问许可
            posixFilePermissions.clear();

            // 日志信息
            String owner = attrs.owner().getName();
            String perms = PosixFilePermissions.toString( posixFilePermissions );
            System.out.format("%s %s%n", owner, perms);

            // 设置新的许可
            posixFilePermissions.add(PosixFilePermission.OWNER_READ);
            posixFilePermissions.add(PosixFilePermission.GROUP_READ);
            posixFilePermissions.add(PosixFilePermission.OTHERS_READ);
            posixFilePermissions.add(PosixFilePermission.OWNER_READ);
            Files.setPosixFilePermissions(profile,posixFilePermissions);



        }catch (IOException e){
            e.printStackTrace();
        }*/



        // 简化文件读取
        Path logfile = Paths.get("F:\\workspace\\shang1\\effectivejava\\effectivejava\\chapter31\\Text.java");
        List<String> _list = Files.readAllLines(logfile,  Charset.forName("GBK"));
        for(String s : _list){
            System.out.println("s:" + s);
        }
        byte[] bytes = Files.readAllBytes(logfile);


    }


}
