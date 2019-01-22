package com.carloswuu.consulclient.consulclient.controller;

import com.carloswuu.consulclient.consulclient.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private HelloService helloService;

    @RequestMapping("/call")
    public String call(){

        logger.info("hitting hello");

        return helloService.hello();
    }
}
