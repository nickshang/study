package desginpatterns.proxy.remote;

import javassist.tools.rmi.RemoteException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * @��������Զ�̽ӿ�ʵ��
 * @��Ŀ���ƣ�shang1
 * @������ proxy_14.remote
 * @�����ƣ�MyRemoteImpl
 * @�����ˣ�NICK
 * @����ʱ�䣺2016��3��10������11:29:45
 * @�޸��ˣ�NICK
 * @�޸�ʱ�䣺2016��3��10������11:29:45
 * @�޸ı�ע��
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 * @mail sjshang@tsingsoft.com
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

	protected MyRemoteImpl() throws java.rmi.RemoteException {
		super();
	}


	/**
	 * @�ֶΣ�serialVersionUID
	 * @�������������к�ID
	 * @�����ˣ�NICK
	 * @����ʱ�䣺2016��3��10������10:27:36
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
