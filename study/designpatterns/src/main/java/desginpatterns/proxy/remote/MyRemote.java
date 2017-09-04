package desginpatterns.proxy.remote;

import java.rmi.Remote;

import javassist.tools.rmi.RemoteException;

/**
 * 
 * @类描述：远程接口
 * <br/>表示此接口要用来支持远程调用
 * @项目名称：shang1
 * @包名： proxy_14.remote
 * @类名称：MyRemote
 * @创建人：NICK
 * @创建时间：2016年3月10日上午10:20:54
 * @修改人：NICK
 * @修改时间：2016年3月10日上午10:20:54
 * @修改备注：
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 * @mail sjshang@tsingsoft.com
 */
public interface MyRemote extends Remote {
	
	public String sayHello() throws RemoteException;
	
}
