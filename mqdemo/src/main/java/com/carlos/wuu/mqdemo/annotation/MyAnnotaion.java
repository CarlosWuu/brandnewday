package com.carlos.wuu.mqdemo.annotation;

import com.carlos.wuu.mqdemo.util.ReadUtil;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: wjb
 * @Date: 2020/4/16 下午8:29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotaion {
}
