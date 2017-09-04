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
     * 注解使用
     * @param i
     * @return
     */
    @Test(timeout=1000000)
    public int get(int i){
        return i*i;
    }

    public static void main(String[] args) {
        U1 u = new U1();

        // 通过类的反射机制获取方法
        Method[] m = u.getClass().getDeclaredMethods();

        // 通过方法获取方法上的注释
        for (Method m1 : m) {
            Test  t = m1.getAnnotation(Test.class);
            if( t != null ){

                // 获取注解的值
                System.out.println("timeout:" + t.timeout() );

                // 如果注解的值为有效值 -> 获取完成后 ->调用类的方法
                try {
                    Field f =  u.getClass().getDeclaredField( "methodname");
                    f.setAccessible(true);

                    // 代理实例的调用处理程序实现的接口
                    InvocationHandler handler = new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            return method.invoke(args);
                        }
                    };

                    // 产生代理对象
                    Test proxy = (Test) Proxy.newProxyInstance(
                            Test.class.getClassLoader(),
                            new Class[]{Test.class},
                            handler);

                    // 代理对象调用
                    // .........

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
