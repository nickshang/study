package com.shang.common;

import java.lang.annotation.*;

/**
 *  标记线程不安全类
 *
 *  注解详解：
 *
 *  {@code@ElementType} 作用：用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
 *  取值(ElementType)有：
 *   1.CONSTRUCTOR:用于描述构造器
 *   2.FIELD:用于描述域
 *   3.LOCAL_VARIABLE:用于描述局部变量
 *   4.METHOD:用于描述方法
 *   5.PACKAGE:用于描述包
 *   6.PARAMETER:用于描述参数
 *   7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
 *
 *   {@code @Retention }　作用：表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）
 *   取值（RetentionPoicy）有：
 *   　1.SOURCE:在源文件中有效（即源文件保留）
 *     2.CLASS:在class文件中有效（即class保留）
 *   　3.RUNTIME:在运行时有效（即运行时保留）
 *
 *   {@code@Documented } 作用： 用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，
 *   因此可以被例如javadoc此类的工具文档化。Documented是一个标记注解，没有成员。
 * Created by Think on 2017/7/15.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotThreadSafe {
}
