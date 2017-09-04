package desginpatterns.proxy.remote;

import java.rmi.Remote;

import javassist.tools.rmi.RemoteException;

/**
 * 
 * @��������Զ�̽ӿ�
 * <br/>��ʾ�˽ӿ�Ҫ����֧��Զ�̵���
 * @��Ŀ���ƣ�shang1
 * @������ proxy_14.remote
 * @�����ƣ�MyRemote
 * @�����ˣ�NICK
 * @����ʱ�䣺2016��3��10������10:20:54
 * @�޸��ˣ�NICK
 * @�޸�ʱ�䣺2016��3��10������10:20:54
 * @�޸ı�ע��
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 * @mail sjshang@tsingsoft.com
 */
public interface MyRemote extends Remote {
	
	public String sayHello() throws RemoteException;
	
}
