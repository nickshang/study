package desginpatterns.proxy.remote;

import javassist.tools.rmi.RemoteException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * @类描述：远程接口实现
 * @项目名称：shang1
 * @包名： proxy_14.remote
 * @类名称：MyRemoteImpl
 * @创建人：NICK
 * @创建时间：2016年3月10日上午11:29:45
 * @修改人：NICK
 * @修改时间：2016年3月10日上午11:29:45
 * @修改备注：
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 * @mail sjshang@tsingsoft.com
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

	protected MyRemoteImpl() throws java.rmi.RemoteException {
		super();
	}


	/**
	 * @字段：serialVersionUID
	 * @功能描述：序列号ID
	 * @创建人：NICK
	 * @创建时间：2016年3月10日上午10:27:36
	 */
	private static final long serialVersionUID = 256360992621467349L;

	
	
	@Override
	public String sayHello() throws RemoteException {
		return "hello!";
	}
	
	
	public static void main(String[] args) throws java.rmi.RemoteException  {
		MyRemote myremote = null; 
		try {
			myremote = new MyRemoteImpl();
		} catch (java.rmi.RemoteException e1) {
			e1.printStackTrace();
		}
		try {
			Naming.rebind("hell0", myremote);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
