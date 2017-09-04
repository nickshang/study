package shang.collection;

import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @类描述：因为多线程环境下，使用HashMap进行put操作会引起死循环，导致CPU利用率接近100%，
 * 所以在并发情况下不能使用HashMap，如以下代码
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年3月23日 上午9:11:04
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class CocurrentHashmapTest {
    public static void main(String[] args) throws Exception  {
		final HashMap<String, String> map = new HashMap<String, String>(2);
		Thread t = new Thread(new Runnable() {
		    @Override
		    public void run() {
		        for (int i = 0; i < 100000; i++) {
		        	// 定义线程
		            new Thread(new Runnable() {
		                @Override
		                public void run() {
		                    map.put(UUID.randomUUID().toString(), "");
		                }
		            }, "ftf" + i).start();
		        }
		    }
		}, "ftf");
		t.start();
		t.join();
//
//        Path logfile = Paths.get("E:\\work\\project\\JavaScript\\33.angluarjs\\2.mvc\\temp.html");
//        List<String> _list = Files.readAllLines(logfile,  Charset.forName("UTF-8"));
//
//        String _path = "E:\\work\\project\\JavaScript\\33.angluarjs\\2.mvc\\";
//
//        OutputStreamWriter osw = null;
//        for (int i = 0; i < 1; i++) {
//            File f = new File( _path + i +".html" );
//            osw = new OutputStreamWriter (new FileOutputStream( f ) ,"UTF-8" );
//
//            String _s = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png";
//            for (String s : _list) {
//                System.out.println("s1:" + s);
//                s = s.replaceAll("菜单图片","<img src=" + _s + ">");
//                s = s.replaceAll("主料","主料：花生");
//                System.out.println("s2:" + s);
//                osw.write(s.toCharArray(),0,s.toCharArray().length);
//            }
//            osw.flush();
//            osw.close();
//        }



    }
}
