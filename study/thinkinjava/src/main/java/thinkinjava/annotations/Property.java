package thinkinjava.annotations;

import java.lang.annotation.*;

/**
 * Created by Think on 2016/6/21.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Property {
    String editor() default "";
}
