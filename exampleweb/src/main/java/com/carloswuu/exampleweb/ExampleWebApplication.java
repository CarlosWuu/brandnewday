package com.carloswuu.exampleweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "com.carloswuu.exampleweb.dao")
public class ExampleWebApplication {
}
