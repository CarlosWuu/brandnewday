package com.carlos.wuu.brandnewday;

import com.carlos.wuu.brandnewday.dao.UserMapperDao;
import com.carlos.wuu.brandnewday.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrandnewdayApplicationTests {
    @Autowired
    private UserMapperDao userMapperDao;

    @Test
    public void contextLoads() {
        List<User> users = userMapperDao.selectUsers();
        users.forEach(user -> System.out.println(user.getAddress()));
        List<User> users2 = userMapperDao.selectUsers();
    }

}

