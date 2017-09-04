package desginpatterns.proxy.reflect;

import java.lang.reflect.Proxy;

/**
 * 
 * @类描述：测试
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年3月12日 下午12:29:33
 * @修改人：NICK
 * @修改时间：2016年3月12日 下午12:29:33
 * @修改备注：
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class MatchMakingTestDrive {
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * 
	 * Title:初始化
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
	 * @描述: 创建Proxy类并实例化Proxy对象
	 * @param person
	 * @return
	 * @返回类型 PersonBean
	 * @创建人 NICK
	 * @创建时间 2016年3月12日 下午1:11:11
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
