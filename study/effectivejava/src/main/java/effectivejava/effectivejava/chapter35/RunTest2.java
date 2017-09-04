package effectivejava.effectivejava.chapter35;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//program to process marker annotations

/**
 * 
 * 利用注解方式替代中命名模式
 * 用测试框架中的例子来进行进行例子说明
 * @author NICK
 */
public class RunTest2 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		int tests = 0;
		int passed = 0;
		
		Class testClass = Class.forName(Sample2.class.getName());
		for (Method m : testClass.getDeclaredMethods()){
			if (m.isAnnotationPresent(ExceptionTest.class)) {
				tests++;
				try{
					m.invoke(null);
					passed++;
				}catch(InvocationTargetException wrappedEx){
					Throwable exc = wrappedEx.getCause();
//					Class<? extends Exception> excType = 
//						m.getAnnotation(ExceptionTest.class).value();
//					
//					if (excType.isInstance(exc)) {
//						passed++;
//					} else {
//						System.out.printf( "Test %s failed : expected %s, got %s%n",
//								m, excType.getName(), exc);
//					}
					
					Class<? extends Exception>[] excTypes = 
						m.getAnnotation(ExceptionTest.class).value();
					
					for(Class<? extends Exception> excType : excTypes ){
						if (excType.isInstance(exc)) {
							passed++;
						} else {
							System.out.printf( "Test %s failed : expected %s, got %s%n",
									m, excType.getName(), exc);
						}
					}
				}catch(Exception exc){
					System.out.println("INVALID @Test: " + m);
				}
			}
		}
		
		System.out.printf("Passed: %d, Failed:%d%n", passed, tests - passed);
	}
}
