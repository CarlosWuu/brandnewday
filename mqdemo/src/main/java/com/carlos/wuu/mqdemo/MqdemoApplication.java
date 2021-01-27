package com.carlos.wuu.mqdemo;

import com.carlos.wuu.mqdemo.annotation.MyAnnotaion;
import com.carlos.wuu.mqdemo.util.ReadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MyAnnotaion
@RestController
public class MqdemoApplication {
    @Autowired
    private ReadUtil readUtil;

    public static void main(String[] args) {

        SpringApplication.run(MqdemoApplication.class,args);

    }

    @GetMapping("/test")
    public void test(){
        readUtil.print();
    }

}
