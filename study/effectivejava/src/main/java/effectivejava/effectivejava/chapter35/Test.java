package effectivejava.effectivejava.chapter35;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * indicates that the annotated methods is a test   —》标注这个是个测试类方法
 * Use only on pararmeterless static methods ->仅限用无参数的户静态方法
 * @author NICK
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
	
}
