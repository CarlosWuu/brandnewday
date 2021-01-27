package com.carlos.wuu.backendserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BackendserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendserverApplication.class, args);
    }

}
