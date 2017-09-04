package desginpatterns.proxy.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @类描述：拥有代理服务
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年3月12日 下午12:06:38
 * @修改人：NICK
 * @修改时间：2016年3月12日 下午12:06:38
 * @修改备注：
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class OwnerInvocationHandler implements InvocationHandler {

	private PersonBean bean;
	
	public OwnerInvocationHandler(PersonBean bean){
		this.bean = bean;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessError {
		try {
			if( method.getName().startsWith("get") ) {
				return method.invoke(bean, args);
			}else  if( method.getName().startsWith("setHotOrNotRating") ){
				throw new IllegalAccessError();
			}else  if( method.getName().startsWith("set") ){
				return method.invoke(bean, args);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

}
