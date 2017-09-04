package desginpatterns.proxy.remote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * 
 * @类描述：远程接口注册
 * @项目名称：shang1
 * @包名： proxy_14.remote
 * @类名称：RemoteRegistry
 * @创建人：NICK
 * @创建时间：2016年3月10日上午11:30:18
 * @修改人：NICK
 * @修改时间：2016年3月10日上午11:30:18
 * @修改备注：
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 * @mail sjshang@tsingsoft.com
 */
public class RemoteRegistry {
	public static void main(String[] args) throws RemoteException {
		MyRemote myremote = new MyRemoteImpl();
		try {
			Naming.rebind("hell0", myremote);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
