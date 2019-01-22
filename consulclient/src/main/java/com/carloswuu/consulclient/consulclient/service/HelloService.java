package com.carloswuu.consulclient.consulclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    @Autowired
    private RestTemplate restTemplate;

    public String hello(){
        return restTemplate.getForObject("http://service-producer/hello",String.class);
    }
}
