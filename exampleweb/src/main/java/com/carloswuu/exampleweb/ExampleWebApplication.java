package com.carloswuu.exampleweb;

import com.carloswuu.exampleweb.annotation.MyAnnotaion;
import com.carloswuu.exampleweb.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;

@SpringBootApplication
//@EnableCaching
//@MapperScan(basePackages = "com.carloswuu.exampleweb.dao")
@MyAnnotaion
//@Controller
public class ExampleWebApplication {

    @Autowired
    private User user;

    public static void main(String[] args) {

        SpringApplication.run(ExampleWebApplication.class,args);

    }


}
