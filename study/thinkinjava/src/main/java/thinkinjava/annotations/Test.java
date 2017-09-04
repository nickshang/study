package thinkinjava.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Think on 2016/6/20.
 */

@Target(ElementType.METHOD)                  //  ע�����ڷ�����
@Retention(RetentionPolicy.RUNTIME)          //  VM����ʱ����������ͨ���������ȡע�����Ϣ
public @interface Test {
    long timeout() default 0L;               //  ע���ϵ�����
}


