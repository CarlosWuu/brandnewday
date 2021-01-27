package com.carloswuu.exampleweb.demo;

import com.alibaba.fastjson.JSONObject;
import com.carloswuu.exampleweb.dao.UserMapperDao;
import com.carloswuu.exampleweb.entity.UserScoreEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserMapperDao dao;

//    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testInsert(){
        Random random = new Random();
        for(int i=1;i<1000000;i++){
            UserScoreEntity entity = new UserScoreEntity();

            entity.setScore(random.nextInt(100));
            entity.setUserId(random.nextInt(100));

            dao.insert(entity);
        }
    }

    @Test
    public void mqTest(){
        JSONObject json = new JSONObject();
        json.put("userIds",11);
        json.put("birthday","2020-02-11 12:12:12");
        json.put("assignmentSceneIds",1);
        rabbitTemplate.convertAndSend("queue.activepoints.user20.receive",json.toJSONString());
    }



}

