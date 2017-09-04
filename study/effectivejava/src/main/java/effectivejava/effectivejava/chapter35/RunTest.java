package effectivejava.effectivejava.chapter35;

//program to process marker annotations
public class RunTest {

	public static void main(String[] args) throws Exception {
		int tests = 0;
		int passed = 0;
		
		System.out.println( Test.class.getName() );
//
//		Class testClass = Class.forName(Sample.class.getName());
//		for (Method m : testClass.getDeclaredMethods()){
//			if (m.isAnnotationPresent(Test.class)) {
//				tests++;
//				try{
//					m.invoke(null);
//					passed++;
//				}catch(InvocationTargetException exception){
//					Throwable exc =  exception.getCause();
//					System.out.println(m + " failed:" + exc);
//				}catch(Exception exc){
//					System.out.println("INVALID @Test: " + m);
//				}
//			}
//		}
		
		System.out.printf("Passed: %d, Failed:%d%n", passed, tests - passed);
	}
}
