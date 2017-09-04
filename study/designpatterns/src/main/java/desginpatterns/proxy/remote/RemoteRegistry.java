package desginpatterns.proxy.remote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * 
 * @��������Զ�̽ӿ�ע��
 * @��Ŀ���ƣ�shang1
 * @������ proxy_14.remote
 * @�����ƣ�RemoteRegistry
 * @�����ˣ�NICK
 * @����ʱ�䣺2016��3��10������11:30:18
 * @�޸��ˣ�NICK
 * @�޸�ʱ�䣺2016��3��10������11:30:18
 * @�޸ı�ע��
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
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
