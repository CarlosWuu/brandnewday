package com.carloswuu.exampleweb.dao;

import com.carloswuu.exampleweb.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapperDao {
    @Cacheable(value = "user")
    List<User> selectUsers();
}
