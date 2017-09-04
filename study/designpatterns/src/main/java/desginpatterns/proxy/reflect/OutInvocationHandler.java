package desginpatterns.proxy.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @����������ӵ�д������
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��3��12�� ����12:06:38
 * @�޸��ˣ�NICK
 * @�޸�ʱ�䣺2016��3��12�� ����12:06:38
 * @�޸ı�ע��
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public class OutInvocationHandler implements InvocationHandler {

	private PersonBean bean;
	
	public OutInvocationHandler(PersonBean bean){
		this.bean = bean;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessError {
		try {
			if( method.getName().startsWith("get") ) {
				return method.invoke(bean, args);
			}else  if( method.getName().startsWith("setHotOrNotRating") ){
				return method.invoke(bean, args);
			}else  if( method.getName().startsWith("set") ){
				throw new IllegalAccessError();
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
