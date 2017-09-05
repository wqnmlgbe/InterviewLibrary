package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AUTHOR：lanchuanke on 17/9/5 15:58
 * 用于标记在Activity上，编译时通过此注解获取到所有需要跳转的Activity，自动装入List集合
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Schema {

    public String name();//点击跳转按钮上显示的文字内容
    public Priority priority() default Priority.NORMAL;
}
