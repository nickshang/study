package java7.nio2;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * 文件修改通知
 * Think on 2016/6/24.
 */
public class WatchServiceTest {
    public static void main(String[] args)  {

        try {
            // 监听实例
            WatchService watcher = FileSystems.getDefault().newWatchService();

            // 路径
            Path dir = FileSystems.getDefault().getPath("d:\\");

            // 注册监听变化的路径
            WatchKey key = dir.register(watcher,ENTRY_MODIFY);

            // 一直监听
            while(true){

                System.out.println(" 监听 ");

                // 获取下一个key极其事件
                key = watcher.take();

                // 检查是否为变化事件
                for(WatchEvent<?> enent : key.pollEvents()){
                    if( enent.kind() == ENTRY_MODIFY){
                        System.out.println( "已经发生修改！" );
                    }
                }

                // 重置监听
                key.reset();
            }

        } catch (InterruptedException | IOException e) {       //
            e.printStackTrace();
        }


    }
}
