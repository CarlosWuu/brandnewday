package com.carloswuu.exampleweb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class SlideCodeService implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(SlideCodeService.class);


    @Value("classpath:static/prepare/*")
    private Resource[] resources;

    @Override
    public void afterPropertiesSet() throws Exception {
        for(Resource resource:resources){
            logger.info(resource.getFilename());
        }
    }
}
