package com.carlos.wuu.brandnewday.dao;

import com.carlos.wuu.brandnewday.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapperDao {
    @Cacheable(value = "user")
    List<User> selectUsers();
}
