package thinkinjava.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Think on 2016/6/20.
 */
public class U1 {

    /**
     * ע��ʹ��
     * @param i
     * @return
     */
    @Test(timeout=1000000)
    public int get(int i){
        return i*i;
    }

    public static void main(String[] args) {
        U1 u = new U1();

        // ͨ����ķ�����ƻ�ȡ����
        Method[] m = u.getClass().getDeclaredMethods();

        // ͨ��������ȡ�����ϵ�ע��
        for (Method m1 : m) {
            Test  t = m1.getAnnotation(Test.class);
            if( t != null ){

                // ��ȡע���ֵ
                System.out.println("timeout:" + t.timeout() );

                // ���ע���ֵΪ��Чֵ -> ��ȡ��ɺ� ->������ķ���
                try {
                    Field f =  u.getClass().getDeclaredField( "methodname");
                    f.setAccessible(true);

                    // ����ʵ���ĵ��ô������ʵ�ֵĽӿ�
                    InvocationHandler handler = new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            return method.invoke(args);
                        }
                    };

                    // �����������
                    Test proxy = (Test) Proxy.newProxyInstance(
                            Test.class.getClassLoader(),
                            new Class[]{Test.class},
                            handler);

                    // ����������
                    // .........

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
