package com.carlos.wuu.backendserver.service;

import com.carlos.wuu.backendserver.dao.UserDao;
import com.carlos.wuu.backendserver.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Transactional()
    public UserInfo queryById(Integer id){
        UserInfo userInfo = userDao.queryById(id);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    public void saveUser(UserInfo userInfo){
        userDao.save(userInfo);
    }

}
