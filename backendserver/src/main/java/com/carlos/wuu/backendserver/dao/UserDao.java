package com.carlos.wuu.backendserver.dao;

import com.carlos.wuu.backendserver.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<UserInfo,Integer> {

    @Query(nativeQuery = true,value = "select * from user where id = :id for update")
    UserInfo queryById(Integer id);
}
