package com.carlos.wuu.brandnewday;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "com.carlos.wuu.brandnewday.dao")
public class BrandnewdayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrandnewdayApplication.class, args);
    }

}

