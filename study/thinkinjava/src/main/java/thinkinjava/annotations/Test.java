package thinkinjava.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Think on 2016/6/20.
 */

@Target(ElementType.METHOD)                  //  注解用在方法上
@Retention(RetentionPolicy.RUNTIME)          //  VM运行时保留，可以通过反编译获取注解的信息
public @interface Test {
    long timeout() default 0L;               //  注释上的属性
}


