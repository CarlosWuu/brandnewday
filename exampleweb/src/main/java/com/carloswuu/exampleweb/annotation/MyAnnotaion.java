package com.carloswuu.exampleweb.annotation;

import com.carloswuu.exampleweb.entity.User;
import com.carloswuu.exampleweb.utils.ReadUtil;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: wjb
 * @Date: 2020/4/16 下午8:29
 */
@Import(User.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotaion {
}
