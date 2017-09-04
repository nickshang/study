package desginpatterns.proxy.reflect;

import java.lang.reflect.Proxy;

/**
 * 
 * @������������
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��3��12�� ����12:29:33
 * @�޸��ˣ�NICK
 * @�޸�ʱ�䣺2016��3��12�� ����12:29:33
 * @�޸ı�ע��
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public class MatchMakingTestDrive {
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * 
	 * Title:��ʼ��
	 * Description:
	 */
	public MatchMakingTestDrive(){
		PersonBean joe = getPersonFromDataBase("Joe JavaBean");
//		OwnerInvocationHandler ownerProxy = getOwnerProxy(joe);
//		System.out.println("Name is " + owerProxy);
		
	}
	
	public PersonBean getPersonFromDataBase(String name){
		PersonBean bean = new PersonBeanImpl();
		bean.setName(name);
		return bean;
	}
	
	/**
	 * 
	 * @����: ����Proxy�ಢʵ����Proxy����
	 * @param person
	 * @return
	 * @�������� PersonBean
	 * @������ NICK
	 * @����ʱ�� 2016��3��12�� ����1:11:11
	 * @since
	 * @throws
	 */
	public PersonBean getOwnerProxy(PersonBean person){
		return (PersonBean) Proxy.newProxyInstance(person.getClass().getClassLoader(), 
				person.getClass().getInterfaces(), 
				new OwnerInvocationHandler(person));
	}
	
//	public OwnerInvocationHandler getOwnerProxy(PersonBean bean){
//		return new OwnerInvocationHandler(bean);
//	}
//	
//	public PersonBean getOuterProxy(PersonBean bean){
//		return (new OutInvocationHandler(bean)).invoke(bean, Method., null);
//	}
}
